package dev.ymkz.demo.core.domain.value;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class IsbnTest {

  @Test
  void testValidIsbn() {
    // given
    String value = "9783161484100";

    // when
    var isbn = Isbn.of(value);

    // then
    assertThat(isbn, is(notNullValue()));
    assertThat(isbn.value(), is(value));
  }

  @Test
  void testInvalidIsbn() {
    // given
    String value = "9783161484101";

    // when
    var exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              Isbn.of(value);
            });

    // then
    assertThat(exception.getMessage(), is("Invalid ISBN-13 format"));
  }

  @Test
  void testInvalidLengthIsbn() {
    // given
    String value = "123456789012";

    // when
    var exception =
        assertThrows(
            StringIndexOutOfBoundsException.class,
            () -> {
              Isbn.of(value);
            });

    // then
    assertThat(exception.getMessage(), is("Index 12 out of bounds for length 12"));
  }
}
