package dev.ymkz.demo.core.domain.value;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class IsbnTest {

  @Test
  void validIsbnShouldBeCreated() {
    Isbn isbn = Isbn.of("9781234567897");
    assertNotNull(isbn);
    assertEquals("9781234567897", isbn.value());
  }

  @Test
  void nullIsbnShouldReturnNull() {
    Isbn isbn = Isbn.of(null);
    assertNull(isbn);
  }

  @Test
  void invalidIsbnShouldThrowException() {
    assertThrows(IllegalArgumentException.class, () -> Isbn.of("123456789012"));
    assertThrows(IllegalArgumentException.class, () -> Isbn.of("9781234567890"));
    assertThrows(IllegalArgumentException.class, () -> Isbn.of("invalidisbn123"));
  }

  @Test
  void isbnWithInvalidChecksumShouldThrowException() {
    assertThrows(IllegalArgumentException.class, () -> Isbn.of("9781234567890"));
  }

  @Test
  void isbnWithNonDigitCharactersShouldThrowException() {
    assertThrows(IllegalArgumentException.class, () -> Isbn.of("97812345678a7"));
  }
}
