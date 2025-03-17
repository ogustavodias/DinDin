package com.dindin.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController {

  @GetMapping
  public ResponseEntity<Boolean> getAllUsers() {
    return ResponseEntity.status(HttpStatus.OK).body(true);
  }

}
