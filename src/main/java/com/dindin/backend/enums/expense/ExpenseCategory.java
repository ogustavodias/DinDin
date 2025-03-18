package com.dindin.backend.enums.expense;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = ExpenseCategoryDeserializer.class)
public enum ExpenseCategory {
  HEALTH, FOOD, TRANSPORTATION, EDUCATION, ENTERTAINMENT, HOUSING, INSURANCE, CLOTHING, SAVINGS, UTILITIES, TAXES,
  LOANS, CHARITY, GAS, TRAVEL, SUBSCRIPTIONS, OTHERS;

  public static boolean isValidCategory(ExpenseCategory category) {
    if (category == null)
      return false;
    try {
      ExpenseCategory.valueOf(category.name().toUpperCase());
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  public static boolean isInvalidCategory(ExpenseCategory category) {
    return !isValidCategory(category);
  }
}
