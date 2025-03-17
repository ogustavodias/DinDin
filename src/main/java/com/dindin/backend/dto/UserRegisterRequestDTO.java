package com.dindin.backend.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterRequestDTO(
    @NotBlank String name,
    @NotBlank String email,
    @NotBlank String password) {
}
