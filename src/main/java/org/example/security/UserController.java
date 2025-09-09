package org.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    //TODO: Find out what this is supposed to do
    @GetMapping("/init")
    public void init() {
        SecUser user = userRepository.findByUsername("user");
        if (user == null) { user = new SecUser(); user.setUsername("user"); user.setPassword("123"); }
        System.out.println("Found user: " + user);
        user.setPassword(passwordEncoder.encode("123"));
        userRepository.save(user);
    }


}
