package com.sfeir.solution2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LanguageResourcesController{

  @GetMapping(path = "test/ca/dosomething")
  public String doSomethingCA(){
    return "every one should see this page";
  }
  @GetMapping(path = "a/b/uk/dosomething")
  public String doSomethingUK(){
    return "every one should see this page";
  }
  @GetMapping(path = "a/au/uk/dosomething")
  public String doSomethingAU(){
    return "every one should see this page";
  }
  @GetMapping(path = "living/in/us/america")
  public String doSomethingUS(){
    return "every one should see this page";
  }
}
