package com.dindin.backend.errors.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dindin.backend.errors.AlreadyRegisteredException;
import com.dindin.backend.errors.LoginException;

@ControllerAdvice
public class GlobalHandlerException {

  private static final Logger logger = LoggerFactory.getLogger(GlobalHandlerException.class);

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Void> globalHandler(Exception e) {
    logger.error("Unexpected error occurred: Exception. " + e.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
  }

  @ExceptionHandler(AlreadyRegisteredException.class)
  public ResponseEntity<Void> alreadyRegisteredHandler(AlreadyRegisteredException e) {
    logger.error(
        String.format("Unexpected error occurred: %s. %s",
            e.getClass().getSimpleName(),
            e.getMessage()));
    return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
  }

  @ExceptionHandler(LoginException.class)
  public ResponseEntity<Void> loginHandler(LoginException e) {
    logger.error(
        String.format("Unexpected error occurred: %s. %s",
            e.getClass().getSimpleName(),
            e.getMessage()));
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Void> handleValidationExceptions(MethodArgumentNotValidException e) {
    logger.error(
        String.format("Unexpected error occurred: %s. %s fields with error",
            e.getClass().getSimpleName(),
            e.getAllErrors().size()));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
  }
}
