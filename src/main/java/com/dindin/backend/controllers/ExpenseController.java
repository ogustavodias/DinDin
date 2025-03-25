package com.dindin.backend.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import com.dindin.backend.dto.ExpenseResponseDTO;
import com.dindin.backend.dto.ListOfExpenseResponse;
import com.dindin.backend.services.ExpenseService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/expense")
public class ExpenseController {

  @Autowired
  private ExpenseService service;

  @PostMapping("/register")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<ExpenseResponseDTO> registerExpense(
      Authentication auth,
      @RequestBody @Valid ExpenseRequestDTO request) {
    ExpenseResponseDTO response = service.registerExpense(auth, request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping()
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<ListOfExpenseResponse> getExpensesOfUser(Authentication auth) {
    List<ExpenseResponseDTO> expenses = service.getExpensesOfUser(auth);
    ListOfExpenseResponse response = ListOfExpenseResponse.builder().expenses(expenses).build();
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/{id}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<ExpenseResponseDTO> getExpenseOfUserById(Authentication auth, @PathVariable Long id) {
    ExpenseResponseDTO response = service.getExpenseOfUserById(auth, id);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PatchMapping("/{id}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<ExpenseResponseDTO> editExpenseOfUserById(
      Authentication auth,
      @PathVariable Long id,
      @RequestBody @Valid ExpenseRequestDTO dto) {
    ExpenseResponseDTO response = service.editExpenseOfUserById(auth, id, dto);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping("/{id}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<ExpenseResponseDTO> deleteExpenseOfUserById(Authentication auth, @PathVariable Long id) {
    ExpenseResponseDTO response = service.deleteExpenseOfUserById(auth, id);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/in-period/{id}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<ListOfExpenseResponse> getExpensesOfUserInPeriod(
      Authentication auth,
      @RequestParam LocalDate from,
      @RequestParam LocalDate to) {
    List<ExpenseResponseDTO> expenses = service.getExpensesOfUserInPeriod(auth, from, to);
    ListOfExpenseResponse response = ListOfExpenseResponse.builder().expenses(expenses).build();
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}
