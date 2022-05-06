package com.sfeir.solution2.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.stream.Collectors;

public abstract class AbstractController {

  public String concatUserNameAndMessage(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken, String message){
    return this.extractUserNameAndRole(usernamePasswordAuthenticationToken) +
      "<br />" +
      message;
  }

  private String extractUserNameAndRole(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken){
    String name = usernamePasswordAuthenticationToken.getName();
    String role = usernamePasswordAuthenticationToken.getAuthorities()
      .stream()
      .map(GrantedAuthority::getAuthority)
      .collect(Collectors.joining(","));
    return String.format("Welcome %s! Your role is %s.", name, role);
  }
}
