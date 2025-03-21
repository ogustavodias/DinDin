package com.dindin.backend.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dindin.backend.dto.ExpenseInsertRequestDTO;
import com.dindin.backend.dto.ExpenseRequestDTO;
import com.dindin.backend.dto.ExpenseResponseDTO;
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

  public ExpenseResponseDTO registerExpense(ExpenseInsertRequestDTO dto) {
    User user = userRepository.findById(dto.getUserId())
        .orElseThrow(() -> new EntityNotFoundException("User not found."));

    Expense toPersist = ExpenseRequestDTO
        .toPersitEntity(dto)
        .user(user)
        .build();

    return ExpenseResponseDTO.fromPersistEntity(expenseRepository.save(toPersist));
  }

  public ExpenseResponseDTO getExpenseById(Long id) {
    Expense expense = expenseRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Expense not found"));

    return ExpenseResponseDTO.fromPersistEntity(expense);
  }

  public List<ExpenseResponseDTO> getAllExpenses() {
    return expenseRepository.findAll()
        .stream()
        .map(ExpenseResponseDTO::fromPersistEntity)
        .toList();
  }

  public ExpenseResponseDTO editExpenseById(Long id, ExpenseRequestDTO dto) {
    Expense toEdit = expenseRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Expense not found."));

    Expense edited = ExpenseRequestDTO
        .toPersitEntity(dto)
        .id(id)
        .user(toEdit.getUser())
        .build();

    return ExpenseResponseDTO.fromPersistEntity(expenseRepository.save(edited));
  }

  public ExpenseResponseDTO deleteExpenseById(Long id) {
    Expense toDelete = expenseRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Expense not found"));

    expenseRepository.delete(toDelete);
    return ExpenseResponseDTO.fromPersistEntity(toDelete);
  }

  public List<ExpenseResponseDTO> getExpensesByUserId(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User not found."));

    return user.getExpenses().stream().map(ExpenseResponseDTO::fromPersistEntity).toList();
  }

  public List<ExpenseResponseDTO> getExpensesByUserIdInPeriod(Long id, LocalDate from, LocalDate to) {
    Optional<User> user = userRepository.findById(id);

    if (user.isEmpty())
      throw new EntityNotFoundException("User not found.");

    if (from == null || to == null)
      throw new IllegalArgumentException("Both 'from' and 'to' dates must be provided.");

    if (from.isAfter(to))
      throw new InvalidPeriodException("Invalid period: 'from' (" + from + ") is after 'to' (" + to + ").");

    return expenseRepository.findByUserIdInPeriod(id, from, to)
        .stream()
        .map(ExpenseResponseDTO::fromPersistEntity)
        .toList();
  }

}
