package com.dindin.backend.errors.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dindin.backend.errors.AlreadyRegisteredException;
import com.dindin.backend.errors.InvalidPeriodException;
import com.dindin.backend.errors.LoginException;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalHandlerException {

  private static final Logger logger = LoggerFactory.getLogger(GlobalHandlerException.class);

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Void> handleOthersExceptions(Exception e) {
    logger.error("Unexpected error occurred: Exception. " + e.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
  }

  @ExceptionHandler(AlreadyRegisteredException.class)
  public ResponseEntity<Void> handleAlreadyRegisteredExceptions(AlreadyRegisteredException e) {
    logger.error(
        String.format("Unexpected error occurred: %s. %s",
            e.getClass().getSimpleName(),
            e.getMessage()));
    return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
  }

  @ExceptionHandler(LoginException.class)
  public ResponseEntity<Void> handleLoginExceptions(LoginException e) {
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

  @ExceptionHandler(InvalidPeriodException.class)
  public ResponseEntity<Void> handleInvalidPeriodExceptions(InvalidPeriodException e) {
    logger.error(
        String.format("Unexpected error occurred: %s. %s",
            e.getClass().getSimpleName(),
            e.getMessage()));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Void> handleEntityNotFoundExceptions(EntityNotFoundException e) {
    logger.error(
        String.format("Unexpected error occurred: %s. %s",
            e.getClass().getSimpleName(),
            e.getMessage()));
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
  }
}
