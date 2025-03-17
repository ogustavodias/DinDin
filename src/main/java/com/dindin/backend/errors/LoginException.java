package com.dindin.backend.errors;

public class LoginException extends RuntimeException {
  public LoginException(String message) {
    super(message);
  }
}
