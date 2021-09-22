package com.sfeir.demo6.controller;

import com.sfeir.demo6.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolController {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JdbcUserDetailsManager userManager;

    @GetMapping("/private")
    public String privateData(Authentication authentication) {
        var login = authentication.getName();
        if (authentication instanceof OAuth2AuthenticationToken) {
            var oauth2User = ((OAuth2AuthenticationToken) authentication).getPrincipal();
            login = (String) oauth2User.getAttributes().get("login");
        }
        return String.format("Welcome %s!", login);
    }

    @GetMapping("/public")
    public String publicData() {
        return "Everyone can see this ;)";
    }

    @PostMapping("/register")
    public void register(@RequestBody UserDTO user) {
        var encodedPassword = encoder.encode(user.getPassword());
        var jdbcUser = new User(user.getName(), encodedPassword, List.of(() -> user.getRole()));
        userManager.createUser(jdbcUser);
    }
}
