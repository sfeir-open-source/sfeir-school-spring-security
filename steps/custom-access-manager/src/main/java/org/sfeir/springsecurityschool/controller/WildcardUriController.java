package org.sfeir.springsecurityschool.controller;

import org.sfeir.springsecurityschool.security.Route;
import org.sfeir.springsecurityschool.security.RoutesProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;

@RestController
public class WildcardUriController {

  private final RoutesProvider routesProvider;

  public WildcardUriController(RoutesProvider routesProvider) {
    this.routesProvider = routesProvider;
  }

  @GetMapping("/hello")
  public ResponseEntity<String> hello(){
    return ResponseEntity.ok().body("Hello");
  }

  @GetMapping("/time")
  public ResponseEntity<Date> time(){
    return ResponseEntity.ok().body(new Date());
  }

  @GetMapping("/random/{randomPath}")
  public ResponseEntity<String> time(@PathVariable(name = "randomPath") String path){
    return ResponseEntity.ok().body("You accessed "+path);
  }

  @PostMapping("/route")
  public ResponseEntity addRoute(@RequestBody Route route){
    routesProvider.addRoute(route);
    return ResponseEntity.accepted().build();
  }

  @GetMapping("/route")
  public ResponseEntity<Set<Route>> addRoute(){
    return ResponseEntity.ok(routesProvider.getRoutes());
  }
}
