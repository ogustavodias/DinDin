package com.dindin.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.dindin.backend.enums.ExpenseCategory;
import com.dindin.backend.models.expense.Expense;

public record ExpenseResponseDTO(
    Long id,
    ExpenseCategory category,
    String description,
    BigDecimal amount,
    LocalDate date) {

  public static ExpenseResponseDTO fromPersistEntity(Expense entity) {
    return new ExpenseResponseDTO(
        entity.getId(),
        entity.getCategory(),
        entity.getDescription(),
        entity.getAmount(),
        entity.getDate());
  }
}
