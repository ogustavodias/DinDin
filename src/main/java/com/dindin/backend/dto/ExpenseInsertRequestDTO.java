package com.dindin.backend.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseInsertRequestDTO extends ExpenseRequestDTO {
  @Min(value = 1)
  private Long userId;
}
