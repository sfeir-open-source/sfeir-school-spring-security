package com.sfeir.solution2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/resource/sensitive")
public class ResourceController {

  @GetMapping()
  public String sensitive(){
    return "This sensitive folder is accessible for authenticated users";
  }

  @PostMapping()
  public String postsensitive(){
    return "This sensitive folder is accessible for authenticated users";
  }

  @PostMapping(path="nop")
  public String sensitiveForbidden(){
    return "No one should see this page";
  }

  @PostMapping(path="test/ok")
  public String sensitiveAccessible(){
    return this.sensitive();
  }
}
