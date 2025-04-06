package com.dindin.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dindin.backend.dto.UserResponseDTO;
import com.dindin.backend.infra.security.TokenProvider;
import com.dindin.backend.models.user.User;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(path = "/user")
public class UserController {

  @Autowired
  TokenProvider tokenProvider;

  @GetMapping()
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<UserResponseDTO> getUserByToken(@AuthenticationPrincipal User user) {
    UserResponseDTO response = UserResponseDTO.fromEntity(user, tokenProvider.geCurrentToken());
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}
