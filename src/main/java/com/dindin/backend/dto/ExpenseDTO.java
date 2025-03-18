package com.dindin.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.dindin.backend.enums.ExpenseCategory;
import com.dindin.backend.models.expense.Expense;
import com.dindin.backend.validators.ExpenseCategoryConstraint;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record ExpenseDTO(
                @ExpenseCategoryConstraint ExpenseCategory category,
                String description,
                @NotNull @Min(value = 1) BigDecimal amount,
                @NotNull @PastOrPresent LocalDate date,
                @NotNull @Min(value = 1) Long userId) {
        public static ExpenseDTO fromPersistEntity(Expense expense) {
                return new ExpenseDTO(
                                expense.getCategory(),
                                expense.getDescription(),
                                expense.getAmount(),
                                expense.getDate(),
                                expense.getUser().getId());
        }
}
