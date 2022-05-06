package com.sfeir.solution2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnonymousController {

  @GetMapping(path="/anonyme")
  public String getAnonyme(){
    return "This is the anonymous URL. You have to be authenticated to see that";
  }
}
