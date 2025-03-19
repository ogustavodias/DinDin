package com.dindin.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dindin.backend.dto.ExpenseDTO;
import com.dindin.backend.models.expense.Expense;
import com.dindin.backend.models.user.User;
import com.dindin.backend.repositories.ExpenseRepository;
import com.dindin.backend.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ExpenseService {

  @Autowired
  private ExpenseRepository eRepository;

  @Autowired
  private UserRepository uRepository;

  public ExpenseDTO registerExpense(ExpenseDTO dto) {
    User user = uRepository.findById(dto.userId())
        .orElseThrow(() -> new EntityNotFoundException("User not found."));

    Expense persistEntity = Expense.builder()
        .category(dto.category())
        .description(dto.description())
        .amount(dto.amount())
        .date(dto.date())
        .user(user)
        .build();

    return ExpenseDTO.fromPersistEntity(eRepository.save(persistEntity));
  }

  public List<ExpenseDTO> getUserExpenses(Long userId) {
    User user = uRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("User not found."));
        
    return user.getExpenses().stream().map(ExpenseDTO::fromPersistEntity).toList();
  }

}
