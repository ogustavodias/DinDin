package com.dindin.backend.enums;

public enum ExpenseCategory {
  HEALTH, FOOD, TRANSPORTATION, EDUCATION, ENTERTAINMENT, HOUSING, INSURANCE, CLOTHING, SAVINGS, UTILITIES, TAXES,
  LOANS, CHARITY, GAS, TRAVEL, SUBSCRIPTIONS, OTHERS;

  public boolean isValidCategory(String category) {
    if (category == null)
      return false;
    try {
      ExpenseCategory.valueOf(category.toUpperCase());
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }
}
