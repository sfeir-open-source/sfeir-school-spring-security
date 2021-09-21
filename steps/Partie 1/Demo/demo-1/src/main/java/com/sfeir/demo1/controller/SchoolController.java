package com.sfeir.demo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolController {

    @GetMapping("/private")
    public String privateData() {
        return "Only authenticated users should see this!";
    }

    @GetMapping("/public")
    public String publicData() {
        return "Everyone can see this ;)";
    }
}
