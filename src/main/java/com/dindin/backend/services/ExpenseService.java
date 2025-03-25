package com.dindin.backend.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.dindin.backend.dto.ExpenseRequestDTO;
import com.dindin.backend.dto.ExpenseResponseDTO;
import com.dindin.backend.errors.InvalidPeriodException;
import com.dindin.backend.errors.LoginException;
import com.dindin.backend.models.expense.Expense;
import com.dindin.backend.models.user.User;
import com.dindin.backend.repositories.ExpenseRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ExpenseService {

  @Autowired
  private ExpenseRepository expenseRepository;

  public ExpenseResponseDTO registerExpense(Authentication auth, ExpenseRequestDTO dto) {
    User user = (User) auth.getPrincipal();

    Expense toPersist = ExpenseRequestDTO
        .toPersitEntity(dto)
        .user(user)
        .build();

    return ExpenseResponseDTO.fromPersistEntity(expenseRepository.save(toPersist));
  }

  public List<ExpenseResponseDTO> getExpensesOfUser(Authentication auth) {
    User user = (User) auth.getPrincipal();

    List<Expense> expenses = expenseRepository.findByUser(user);

    return expenses.stream().map(ExpenseResponseDTO::fromPersistEntity).toList();
  }

  public List<ExpenseResponseDTO> getExpensesOfUserInPeriod(
      Authentication auth,
      LocalDate from,
      LocalDate to) {

    User user = (User) auth.getPrincipal();

    if (from == null || to == null)
      throw new IllegalArgumentException("Both 'from' and 'to' dates must be provided.");

    if (from.isAfter(to))
      throw new InvalidPeriodException("Invalid period: 'from' (" + from + ") is after 'to' (" + to + ").");

    return expenseRepository.findByUserIdInPeriod(user.getId(), from, to)
        .stream()
        .map(ExpenseResponseDTO::fromPersistEntity)
        .toList();
  }

  public ExpenseResponseDTO getExpenseOfUserById(Authentication auth, Long id) {
    User user = (User) auth.getPrincipal();

    Expense expense = expenseRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Expense not found"));

    if (user.getId() != expense.getUser().getId())
      throw new LoginException("Unauthorized");

    return ExpenseResponseDTO.fromPersistEntity(expense);
  }

  public ExpenseResponseDTO editExpenseOfUserById(Authentication auth, Long id, ExpenseRequestDTO dto) {
    User user = (User) auth.getPrincipal();

    Expense expense = expenseRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Expense not found."));

    if (user.getId() != expense.getUser().getId())
      throw new LoginException("Unauthorized");

    expense = ExpenseRequestDTO
        .toPersitEntity(dto)
        .id(id)
        .user(expense.getUser())
        .build();

    return ExpenseResponseDTO.fromPersistEntity(expenseRepository.save(expense));
  }

  public ExpenseResponseDTO deleteExpenseOfUserById(Authentication auth, Long id) {
    User user = (User) auth.getPrincipal();

    Expense expense = expenseRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Expense not found"));

    if (user.getId() != expense.getUser().getId())
      throw new LoginException("Unauthorized");

    expenseRepository.delete(expense);
    return ExpenseResponseDTO.fromPersistEntity(expense);
  }

}
