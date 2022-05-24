package org.sfeir.springsecurity.oauth2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Oauth2Controller {

  @GetMapping("/secure")
  public String securedApi(){
    return"You accessed a secured api";
  }

  @GetMapping("/super-secure")
  public String secretApi(){
    return "You accessed a super secured api,which requires the admin scope";
  }
  @GetMapping("/public")
  public String getPublic(){
    return "Hello World!";
  }
}
