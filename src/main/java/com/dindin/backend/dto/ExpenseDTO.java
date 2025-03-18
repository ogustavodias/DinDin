package com.dindin.backend.dto;

import java.math.BigDecimal;

import com.dindin.backend.enums.expense.ExpenseCategory;
import com.dindin.backend.models.expense.Expense;
import com.dindin.backend.validators.ExpenseCategoryConstraint;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ExpenseDTO(
                @ExpenseCategoryConstraint ExpenseCategory category,
                String description,
                @NotNull @Min(value = 1) BigDecimal amount,
                @NotNull @Min(value = 1) Long userId) {
        public static ExpenseDTO fromPersistEntity(Expense expense) {
                return new ExpenseDTO(
                                expense.getCategory(),
                                expense.getDescription(),
                                expense.getAmount(),
                                expense.getUser().getId());
        }
}
