package com.dindin.backend.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ListOfExpenseResponse {
  private List<ExpenseResponseDTO> expenses;
}
