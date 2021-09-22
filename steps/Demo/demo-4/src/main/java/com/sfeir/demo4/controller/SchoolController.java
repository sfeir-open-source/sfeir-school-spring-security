package com.sfeir.demo4.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolController {

    @GetMapping("/private")
    public String privateData(UsernamePasswordAuthenticationToken user) {
        return String.format("Welcome %s!", user.getName());
    }

    @GetMapping("/public")
    public String publicData() {
        return "Everyone can see this ;)";
    }
}
