package com.dindin.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    ExpenseDTO response = service.registerExpense(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping
  public ResponseEntity<List<ExpenseDTO>> registerExpense(@RequestParam Long userId) {
    List<ExpenseDTO> response = service.getUserExpenses(userId);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

}
