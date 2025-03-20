package com.dindin.backend.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegisterRequestDTO(
                @NotBlank String name,
                @NotBlank @Email(message = "Invalid email format") String email,
                @NotBlank @Length(min = 8) String password) {
}
