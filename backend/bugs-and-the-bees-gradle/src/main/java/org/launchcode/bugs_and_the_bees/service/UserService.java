package org.launchcode.bugs_and_the_bees.service;

import org.launchcode.bugs_and_the_bees.data.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
