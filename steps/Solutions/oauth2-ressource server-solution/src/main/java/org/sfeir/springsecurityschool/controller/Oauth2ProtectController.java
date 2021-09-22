package org.sfeir.springsecurityschool.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Oauth2ProtectController {

  @GetMapping("public")
  public ResponseEntity<String> publicApi(){
    return ResponseEntity.ok("You accessed a public api");
  }

  @GetMapping("secure")
  public ResponseEntity<String> securedApi(){
    return ResponseEntity.ok("You accessed a secured api");
  }

  @GetMapping("super-secure")
  public ResponseEntity<String> secretApi(){
    return ResponseEntity.ok("You accessed a super secured api,which requires the admin scope");
  }
}
