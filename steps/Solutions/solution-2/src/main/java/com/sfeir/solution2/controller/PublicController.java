package com.sfeir.solution2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

  @GetMapping(path="/public")
  public String hello(){
    return "URL Public visible par tout le monde";
  }
}
