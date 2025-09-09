package org.example.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AppController {
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    public AppController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/secret")
    public String secret(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails userDetails) {
                model.addAttribute("userName", userDetails.getUsername());
            }
        }
        return "secret";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/register")
    public String register(Model model) {
        System.out.println("Serving register page...");
        SecUser user = new SecUser();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute SecUser user) {
        System.out.println("Adding user with username " + user.getUsername() + " password " + user.getPassword());
        user.setPassword(passwordEncoder.encode("123"));
        userRepository.save(user);
        return "redirect:/home";
    }

}
