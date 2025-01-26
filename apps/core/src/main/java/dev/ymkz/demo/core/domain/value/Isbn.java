package dev.ymkz.demo.core.domain.value;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record Isbn(@NotBlank @Size(min = 13, max = 13) String value) {
  public Isbn {
    if (!isValid(value)) {
      throw new IllegalArgumentException("Invalid ISBN-13 format");
    }
  }

  public static Isbn of(String value) {
    if (value == null) {
      return null;
    }
    return new Isbn(value);
  }

  private static boolean isValid(String value) {
    int sum = 0;
    for (int i = 0; i < 12; i++) {
      int digit = Character.getNumericValue(value.charAt(i));
      sum += digit * (i % 2 == 0 ? 1 : 3);
    }

    int checkDigit = Character.getNumericValue(value.charAt(12));
    return (10 - (sum % 10)) % 10 == checkDigit;
  }
}
