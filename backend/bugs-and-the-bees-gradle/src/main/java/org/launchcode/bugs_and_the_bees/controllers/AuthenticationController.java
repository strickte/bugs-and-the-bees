package org.launchcode.bugs_and_the_bees.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.launchcode.bugs_and_the_bees.data.UserRepository;
import org.launchcode.bugs_and_the_bees.models.User;
import org.launchcode.bugs_and_the_bees.models.dto.RegisterFormDTO;
import org.launchcode.bugs_and_the_bees.models.dto.LoginFormDTO;
import org.launchcode.bugs_and_the_bees.models.dto.UserDTO;
import org.launchcode.bugs_and_the_bees.models.dto.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(value = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/api")
@RestController
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey = "user";

//    public User getUserFromSession(HttpSession session) {
//        Integer userId = (Integer) session.getAttribute(userSessionKey);
//        if (userId == null) {
//            return null;
//        }
//
//        Optional<User> user = userRepository.findById(userId);
//        if (user.isEmpty()) {
//            return null;
//        }
//        return user.get();
//    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> processRegistrationForm(@RequestBody @Valid RegisterFormDTO registerFormDTO, Errors errors, HttpServletRequest request) {
        Map<String, String> response = new HashMap<>();

        //Validation errors from @Valid using lambda expression
        if (errors.hasErrors()) {
            Map<String, String> validationErrors = new HashMap<>();
            errors.getFieldErrors().forEach(error -> validationErrors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(validationErrors);
        }

        //Check is username already exists
       if (userRepository.existsByUsername(registerFormDTO.getUsername())) {
           response.put("message", "Username already exists");
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
       }

       //Verify password and confirmPassword match
       String password = registerFormDTO.getPassword();
       String confirmPassword = registerFormDTO.getConfirmPassword();
       if (!password.equals(confirmPassword)) {
           response.put("message", "Passwords do not match");
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
       }

       //Save user
       User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword());
       userRepository.save(newUser);

       //Set user in session
       setUserInSession(request.getSession(), newUser);

       return ResponseEntity.ok(Map.of("message", "Successfully registered and logged in"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> processLoginForm(@RequestBody @Valid LoginFormDTO loginFormDTO, Errors errors, HttpServletRequest request) {

        //Validation errors from @Valid using lambda expression
            if (errors.hasErrors()) {
            Map<String, String> validationErrors = new HashMap<>();
            errors.getFieldErrors().forEach(error -> validationErrors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(validationErrors);
        }

        //Check user exists
        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());
        if (theUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message","Username does not exist"));
        }

        //Validate password
        String password = loginFormDTO.getPassword();
        if (!theUser.isMatchingPassword(password)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Invalid password"));
        }

        //Set user in session and LoginResponseDTO
        setUserInSession(request.getSession(), theUser);
        UserDTO userDTO = new UserDTO(theUser);
        LoginResponseDTO responseDTO = new LoginResponseDTO("Successfully logged in", userDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("signout")
    public ResponseEntity<?> processSignout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseEntity.ok(Map.of("message", "Signed out successfully"));
    }
}


