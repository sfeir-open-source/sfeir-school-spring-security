package org.sfeir.springsecurityschool.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sébastien DE SANTIS, SFEIR Benelux
 * @version 1.0
 * @since 4/21/22
 */
@RestController
public class HelloController {

  @GetMapping("/hello")
  public ResponseEntity<String> getHello(){
    return ResponseEntity.ok("Hello !");
  }

}
