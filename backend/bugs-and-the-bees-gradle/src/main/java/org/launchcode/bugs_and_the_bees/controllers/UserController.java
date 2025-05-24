package org.launchcode.bugs_and_the_bees.controllers;

import org.launchcode.bugs_and_the_bees.data.UserRepository;
import org.launchcode.bugs_and_the_bees.models.User;
import org.launchcode.bugs_and_the_bees.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
               return userService.createUser(user);
    }
}
