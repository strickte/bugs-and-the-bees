package org.launchcode.bugs_and_the_bees.service;

import org.launchcode.bugs_and_the_bees.data.UserRepository;
import org.launchcode.bugs_and_the_bees.models.Project;
import org.launchcode.bugs_and_the_bees.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
