package org.sfeir.springsecurityschool.controller;

import org.sfeir.springsecurityschool.service.UuidLookUpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final UuidLookUpService uuidLookUpService;

    public HelloController(UuidLookUpService uuidLookUpService) {
        this.uuidLookUpService = uuidLookUpService;
    }

    @GetMapping("/hello/{name}")
    public ResponseEntity<String> getName(@PathVariable String name){
        return ResponseEntity.ok(name);
    }

    @PostMapping("/hello/{name}")
    public ResponseEntity<String> registerName(@PathVariable String name){
        return ResponseEntity.ok(uuidLookUpService.addValue("name",name));
    }
}
