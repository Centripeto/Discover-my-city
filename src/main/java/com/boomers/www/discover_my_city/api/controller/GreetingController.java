package com.boomers.www.discover_my_city.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class GreetingController {
  private static final String template = "Hello, %s!";

  public record Greeting(String content) {}

  @GetMapping("/greet")
  public ResponseEntity<Greeting> greet(
      @RequestParam(value = "name", defaultValue = "World") String name) {
    return ResponseEntity.ok(new Greeting(String.format(template, name)));
  }
}
