package com.sfeir.solution2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivateController {

  @GetMapping(path="/private")
  public String get(){
    return "Only user with roles ADMIN or SFEIR can see this";
  }
}
