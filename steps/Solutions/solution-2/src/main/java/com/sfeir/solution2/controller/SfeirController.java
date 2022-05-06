package com.sfeir.solution2.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SfeirController extends AbstractController{

  @GetMapping(path = "sfeir/something")
  public String doSomething(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken){
    return super.concatUserNameAndMessage(usernamePasswordAuthenticationToken, "Les utilisateurs ayant un rôle SFEIR ou ADMIN peuvent visualiser cette page");
  }

  @GetMapping(path="sfeir/special")
  public String special(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken){
    return super.concatUserNameAndMessage(usernamePasswordAuthenticationToken, "Les utilisateurs ayant un rôle SFEIR peuvent visualiser cette page et pas ceux qui ont le rôle ADMIN");
  }

  @GetMapping(path="sfeir/special/else")
  public String specialElse(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken){
    return special(usernamePasswordAuthenticationToken);
  }
}
