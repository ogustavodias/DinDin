package com.dindin.backend.dto;

import com.dindin.backend.models.user.User;

public record UserResponseDTO(String name, String email, String token) {

  public static UserResponseDTO fromEntity(User user, String token) {
    return new UserResponseDTO(user.getName(), user.getEmail(), token);
  }
}
