package com.dindin.backend.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @PostMapping("/register")
  public ResponseEntity<ExpenseDTO> registerExpense(@RequestBody @Valid ExpenseDTO request) {
    ExpenseDTO response = service.registerExpense(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping()
  public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
    List<ExpenseDTO> response = service.getAllExpenses();
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable Long id) {
    ExpenseDTO response = service.getExpenseById(id);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ExpenseDTO> editExpenseById(
      @PathVariable Long id,
      @RequestBody @Valid ExpenseDTO dto) {
    ExpenseDTO response = service.editExpenseById(id, dto);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ExpenseDTO> deleteExpenseById(@PathVariable Long id) {
    ExpenseDTO response = service.deleteExpenseById(id);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/by-user/{id}")
  public ResponseEntity<List<ExpenseDTO>> getExpensesByUserId(@PathVariable Long id) {
    List<ExpenseDTO> response = service.getExpensesByUserId(id);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/by-user/in-period/{id}")
  public ResponseEntity<List<ExpenseDTO>> getExpensesByUserIdInPeriod(
      @PathVariable Long id,
      @RequestParam LocalDate from,
      @RequestParam LocalDate to) {
    List<ExpenseDTO> response = service.getExpensesByUserIdInPeriod(id, from, to);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}
