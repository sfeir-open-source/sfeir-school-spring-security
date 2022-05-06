package com.sfeir.solution2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForbiddenController {

  @GetMapping(path="forbidden")
  public String getForbidden(){
    return "This path is \"Forbidden\" and nobody can see it";
  }

  @GetMapping(path="access-denied")
  public String gteAccessDenied() {
    return "This path is \"Access Denied\" and nobody can see it";
  }
}
