package com.dindin.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegisterRequestDTO(
        @NotBlank String name,
        @NotBlank @Email(message = "Invalid email format") String email,
        @NotBlank String password) {
}
