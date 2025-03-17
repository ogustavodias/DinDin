package com.dindin.backend.errors;

public class AlreadyRegisteredException extends RuntimeException {
  public AlreadyRegisteredException(String message) {
    super(message);
  }
}