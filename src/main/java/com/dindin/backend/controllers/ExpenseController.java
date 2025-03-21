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

import com.dindin.backend.dto.ExpenseRequestDTO;
import com.dindin.backend.dto.ExpenseInsertRequestDTO;
import com.dindin.backend.dto.ExpenseResponseDTO;
import com.dindin.backend.dto.ListOfExpenseResponse;
import com.dindin.backend.services.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/expense")
public class ExpenseController {

  @Autowired
  private ExpenseService service;

  @PostMapping("/register")
  public ResponseEntity<ExpenseResponseDTO> registerExpense(@RequestBody @Valid ExpenseInsertRequestDTO request) {
    ExpenseResponseDTO response = service.registerExpense(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping()
  public ResponseEntity<ListOfExpenseResponse> getAllExpenses() {
    List<ExpenseResponseDTO> expenses = service.getAllExpenses();
    ListOfExpenseResponse response = ListOfExpenseResponse.builder().expenses(expenses).build();
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ExpenseResponseDTO> getExpenseById(@PathVariable Long id) {
    ExpenseResponseDTO response = service.getExpenseById(id);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ExpenseResponseDTO> editExpenseById(
      @PathVariable Long id,
      @RequestBody @Valid ExpenseRequestDTO dto) {
    ExpenseResponseDTO response = service.editExpenseById(id, dto);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ExpenseResponseDTO> deleteExpenseById(@PathVariable Long id) {
    ExpenseResponseDTO response = service.deleteExpenseById(id);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/by-user/{id}")
  public ResponseEntity<ListOfExpenseResponse> getExpensesByUserId(@PathVariable Long id) {
    List<ExpenseResponseDTO> expenses = service.getExpensesByUserId(id);
    ListOfExpenseResponse response = ListOfExpenseResponse.builder().expenses(expenses).build();
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/by-user/in-period/{id}")
  public ResponseEntity<ListOfExpenseResponse> getExpensesByUserIdInPeriod(
      @PathVariable Long id,
      @RequestParam LocalDate from,
      @RequestParam LocalDate to) {
    List<ExpenseResponseDTO> expenses = service.getExpensesByUserIdInPeriod(id, from, to);
    ListOfExpenseResponse response = ListOfExpenseResponse.builder().expenses(expenses).build();
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}
