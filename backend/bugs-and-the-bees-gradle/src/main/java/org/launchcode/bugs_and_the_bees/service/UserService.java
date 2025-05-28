package org.launchcode.bugs_and_the_bees.service;

import org.launchcode.bugs_and_the_bees.data.UserRepository;
import org.launchcode.bugs_and_the_bees.models.Project;
import org.launchcode.bugs_and_the_bees.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        String pwHash = encoder.encode(user.getPassword());
        User dbUser = new User(user.getUsername(), pwHash);
        return userRepository.save(dbUser);
    }


}
