package com.dindin.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dindin.backend.models.expense.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
