package com.sfeir.demo5.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolController {

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
}
