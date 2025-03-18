package com.dindin.backend.validators;

import com.dindin.backend.enums.expense.ExpenseCategory;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExpenseCategoryValidator implements
    ConstraintValidator<ExpenseCategoryConstraint, ExpenseCategory> {

  @Override
  public boolean isValid(ExpenseCategory value, ConstraintValidatorContext context) {
    return ExpenseCategory.isValidCategory(value);
  }
}
