package com.dindin.backend.configs;

import java.io.IOException;
import java.util.Optional;
import java.util.Arrays;

import com.dindin.backend.enums.ExpenseCategory;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Custom deserializer of ExpenseCategory to prevent Jackson from throwing
 * an exception and delegate this responsibility to custom validations.
 */
public class ExpenseCategoryDeserializer extends JsonDeserializer<ExpenseCategory> {

  @Override
  public ExpenseCategory deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
    String value = Optional.ofNullable(p.getText()).orElse("").trim();

    return Arrays.stream(ExpenseCategory.values())
        .filter(type -> type.name().equalsIgnoreCase(value))
        .findFirst()
        .orElseGet(() -> null);
  }
}