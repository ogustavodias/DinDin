package com.dindin.backend.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dindin.backend.dto.LoginRequestDTO;
import com.dindin.backend.dto.UserResponseDTO;
import com.dindin.backend.errors.AlreadyRegisteredException;
import com.dindin.backend.errors.LoginException;
import com.dindin.backend.dto.UserRegisterRequestDTO;
import com.dindin.backend.infra.security.TokenService;
import com.dindin.backend.models.user.User;
import com.dindin.backend.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private UserRepository repository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private TokenService tokenService;

  @PostMapping(path = "/login")
  public ResponseEntity<UserResponseDTO> login(@RequestBody @Valid LoginRequestDTO body) {
    Optional<User> user = this.repository.findByEmail(body.email());

    if (user.isEmpty() || !passwordEncoder.matches(body.password(), user.get().getPassword()))
      throw new LoginException("Login failed, please check email and password.");

    // Else
    return ResponseEntity.status(HttpStatus.OK)
        .body(new UserResponseDTO(user.get().getName(), this.tokenService.generateToken(user.get())));
  }

  @PostMapping(path = "/register")
  public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRegisterRequestDTO body) {
    Optional<User> user = this.repository.findByEmail(body.email());

    if (user.isPresent())
      throw new AlreadyRegisteredException("There is already a user registered with this email");

    // Else
    User newUser = this.repository.save(User.builder()
        .name(body.name())
        .email(body.email())
        .password(passwordEncoder.encode(body.password()))
        .build());

    UserResponseDTO response = new UserResponseDTO(newUser.getName(), this.tokenService.generateToken(newUser));
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}
