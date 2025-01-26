package dev.ymkz.demo.core.domain.value;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class RangeTimeTest {

  @Test
  void givenValidRange_whenCreatingRangeTime_thenNoException() {
    // given
    var start = LocalDateTime.of(2023, 1, 1, 0, 0);
    var end = LocalDateTime.of(2023, 1, 2, 0, 0);

    // when
    RangeTime range = RangeTime.of(start, end);

    // then
    assertThat(range.start(), is(start));
    assertThat(range.end(), is(end));
  }

  @Test
  void givenInvalidRange_whenCreatingRangeTime_thenThrowException() {
    // given
    var start = LocalDateTime.of(2023, 1, 2, 0, 0);
    var end = LocalDateTime.of(2023, 1, 1, 0, 0);

    // when
    IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
      IllegalArgumentException.class,
      () -> RangeTime.of(start, end)
    );

    // then
    assertThat(exception.getMessage(), is("The start value is greater than the end value"));
  }
}
