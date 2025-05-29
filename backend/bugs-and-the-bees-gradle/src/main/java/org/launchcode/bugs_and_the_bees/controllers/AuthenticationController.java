package org.launchcode.bugs_and_the_bees.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.launchcode.bugs_and_the_bees.data.UserRepository;
import org.launchcode.bugs_and_the_bees.models.User;
import org.launchcode.bugs_and_the_bees.models.dto.RegisterFormDTO;
import org.launchcode.bugs_and_the_bees.models.dto.LoginFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("http://localhost:5173")
@RequestMapping("/api")
@RestController
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> processRegistrationForm(@RequestBody @Valid RegisterFormDTO registerFormDTO, Errors errors, HttpServletRequest request) throws BadRequestException {

        //Validation errors from @Valid
        if (errors.hasErrors()) {
            Map<String, String> validationErrors = new HashMap<>();
            errors.getFieldErrors().forEach(error -> validationErrors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(validationErrors);
        }

        //Check is username already exists
       if (userRepository.existsByUsername(registerFormDTO.getUsername())) {
           throw new BadRequestException("Username already exists");
       }

       //Verify password and confirmPassword match
       String password = registerFormDTO.getPassword();
       String confirmPassword = registerFormDTO.getConfirmPassword();
       if (!password.equals(confirmPassword)) {
           throw new BadRequestException("Passwords do not match");
       }

       //Save user
       User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword());
       userRepository.save(newUser);

       //Set user in session
       setUserInSession(request.getSession(), newUser);

       return ResponseEntity.ok(Map.of("message", "User successfully registered"));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> processLoginForm(@RequestBody @Valid LoginFormDTO loginFormDTO, Errors errors, HttpServletRequest request) throws BadRequestException {
        //Validation errors from @Valid
        if (errors.hasErrors()) {
            Map<String, String> validationErrors = new HashMap<>();
            errors.getFieldErrors().forEach(error -> validationErrors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(validationErrors);
        }

        //Check user exists
        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());
        if (theUser == null) {
            throw new BadRequestException("Username does not exist");
        }

        //Validate password
        String password = loginFormDTO.getPassword();
        if (!theUser.isMatchingPassword(password)) {
            throw new BadRequestException("Invalid password");
        }

        //Set user in session
        setUserInSession(request.getSession(), theUser);

        return ResponseEntity.ok(Map.of("message", "Successfully logged in"));
    }

    @GetMapping("signout")
    public ResponseEntity<?> processSignout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseEntity.ok(Map.of("message", "Signed out successfully"));
    }
}


