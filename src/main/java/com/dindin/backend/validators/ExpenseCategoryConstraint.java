package com.dindin.backend.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ExpenseCategoryValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExpenseCategoryConstraint {
  String message() default "Invalid expense category.";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
