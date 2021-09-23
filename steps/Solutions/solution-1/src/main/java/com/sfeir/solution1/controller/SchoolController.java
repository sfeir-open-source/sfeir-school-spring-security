package com.sfeir.solution1.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class SchoolController {

    @GetMapping("/private")
    public String privateData(UsernamePasswordAuthenticationToken user) {
        String name = user.getName();
        String roles = !CollectionUtils.isEmpty(user.getAuthorities()) ?
                user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", ")) :
                "null";
        return String.format("Welcome %s! Your roles are %s.", name, roles);
    }

    @GetMapping("/public")
    public String publicData() {
        return "Everyone can see this ;)";
    }
}
