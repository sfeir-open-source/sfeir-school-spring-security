package com.sfeir.solution2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

  @GetMapping(path = "something")
  public String doSomething(){
    return "This something can be done only by an Admin";
  }

  @GetMapping(path = "something/else")
  public String doSomethingElse(){
    return "This something else can be done only by an Admin";
  }

  @GetMapping(path = "whatever")
  public String doWhatever(){
    return "This whatever action can be done only by an Admin";
  }

  @GetMapping(path = "forbidden/release/protomolecule")
  public String releaseProtomolecule(){
    return "This action can be done only by an admin or JP Mao";
  }

  @GetMapping(path = "mib/forbidden/pushRedButton")
  public String pushRedButton(){
    return "Never touch the red button kid!";
  }
}
