package com.dindin.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.dindin.backend.enums.ExpenseCategory;
import com.dindin.backend.models.expense.Expense;
import com.dindin.backend.validators.ExpenseCategoryConstraint;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseRequestDTO {
  @ExpenseCategoryConstraint
  private ExpenseCategory category;

  @NotNull
  @Min(value = 1)
  private BigDecimal amount;

  @NotNull
  @PastOrPresent
  private LocalDate date;
  private String description;

  public static Expense.ExpenseBuilder toPersitEntity(ExpenseRequestDTO dto) {
    return Expense.builder()
        .id(null) // Change in call
        .category(dto.getCategory())
        .amount(dto.getAmount())
        .description(dto.getDescription())
        .date(dto.getDate())
        .user(null); // Change in call
  }
}
