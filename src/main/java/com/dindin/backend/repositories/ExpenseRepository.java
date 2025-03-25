package com.dindin.backend.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dindin.backend.models.expense.Expense;
import com.dindin.backend.models.user.User;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

  List<Expense> findByUser(User user);

  @Query("SELECT e FROM Expense e WHERE e.user.id = :userId AND e.date BETWEEN :from AND :to")
  List<Expense> findByUserIdInPeriod(
      @Param("userId") Long userId,
      @Param("from") LocalDate from,
      @Param("to") LocalDate to);
}
