package com.dindin.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dindin.backend.dto.ExpenseDTO;
import com.dindin.backend.services.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/expense")
public class ExpenseController {

  @Autowired
  private ExpenseService service;

  @PostMapping
  public ResponseEntity<ExpenseDTO> registerExpense(@RequestBody @Valid ExpenseDTO request) {
    ExpenseDTO response = ExpenseDTO.fromPersistEntity(service.registerExpense(request));
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

}
