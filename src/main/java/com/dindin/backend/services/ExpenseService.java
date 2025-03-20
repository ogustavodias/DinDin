package com.dindin.backend.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dindin.backend.dto.ExpenseDTO;
import com.dindin.backend.errors.InvalidPeriodException;
import com.dindin.backend.models.expense.Expense;
import com.dindin.backend.models.user.User;
import com.dindin.backend.repositories.ExpenseRepository;
import com.dindin.backend.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ExpenseService {

  @Autowired
  private ExpenseRepository expenseRepository;

  @Autowired
  private UserRepository userRepository;

  public ExpenseDTO registerExpense(ExpenseDTO dto) {
    User user = userRepository.findById(dto.userId())
        .orElseThrow(() -> new EntityNotFoundException("User not found."));

    Expense persistEntity = Expense.builder()
        .category(dto.category())
        .description(dto.description())
        .amount(dto.amount())
        .date(dto.date())
        .user(user)
        .build();

    return ExpenseDTO.fromPersistEntity(expenseRepository.save(persistEntity));
  }

  public ExpenseDTO getExpenseById(Long id) {
    Expense expense = expenseRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Expense not found"));

    return ExpenseDTO.fromPersistEntity(expense);
  }

  public List<ExpenseDTO> getAllExpenses() {
    return expenseRepository.findAll()
        .stream()
        .map(ExpenseDTO::fromPersistEntity)
        .toList();
  }

  public ExpenseDTO editExpenseById(Long id, ExpenseDTO dto) {
    Expense toEdit = expenseRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Expense not found."));

    Expense edited = Expense.builder()
        .id(id)
        .category(dto.category())
        .description(dto.description())
        .amount(dto.amount())
        .date(dto.date())
        .user(toEdit.getUser())
        .build();

    return ExpenseDTO.fromPersistEntity(expenseRepository.save(edited));
  }

  public ExpenseDTO deleteExpenseById(Long id) {
    Expense toDelete = expenseRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Expense not found"));

    expenseRepository.delete(toDelete);
    return ExpenseDTO.fromPersistEntity(toDelete);
  }

  public List<ExpenseDTO> getExpensesByUserId(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User not found."));

    return user.getExpenses().stream().map(ExpenseDTO::fromPersistEntity).toList();
  }

  public List<ExpenseDTO> getExpensesByUserIdInPeriod(Long id, LocalDate from, LocalDate to) {
    Optional<User> user = userRepository.findById(id);

    if (user.isEmpty())
      throw new EntityNotFoundException("User not found.");

    if (from == null || to == null)
      throw new IllegalArgumentException("Both 'from' and 'to' dates must be provided.");

    if (from.isAfter(to))
      throw new InvalidPeriodException("Invalid period: 'from' (" + from + ") is after 'to' (" + to + ").");

    return expenseRepository.findByUserIdInPeriod(id, from, to)
        .stream()
        .map(ExpenseDTO::fromPersistEntity)
        .toList();
  }

}
