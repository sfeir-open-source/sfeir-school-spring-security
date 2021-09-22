package com.sfeir.exercice1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SchoolController {

    @GetMapping("/private")
    public String privateData(Principal authentication) {
        String name = authentication.getName();
        String role = "?"; // TODO Get the role
        return String.format("Welcome %s! Your role is %s.", name, role);
    }

    @GetMapping("/public")
    public String publicData() {
        return "Everyone can see this ;)";
    }
}
