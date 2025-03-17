package com.dindin.backend.models.expense;

import java.math.BigDecimal;

import com.dindin.backend.enums.ExpenseCategory;
import com.dindin.backend.models.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private BigDecimal amount;
  private ExpenseCategory category;
  private String description;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonBackReference
  private User user;
}
