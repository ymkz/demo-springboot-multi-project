package dev.ymkz.demo.core.domain.value;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.Test;

class RangeTimeTest {

  @Test
  void givenValidRange_whenCreatingRangeTime_thenNoException() {
    // given
    var start = LocalDateTime.of(2023, 1, 1, 0, 0).atZone(ZoneId.of("Asia/Tokyo")).toInstant();
    var end = LocalDateTime.of(2023, 1, 2, 0, 0).atZone(ZoneId.of("Asia/Tokyo")).toInstant();

    // when
    RangeTime range = RangeTime.of(start, end);

    // then
    assertThat(range.start(), is(LocalDateTime.ofInstant(start, ZoneId.of("Asia/Tokyo"))));
    assertThat(range.end(), is(LocalDateTime.ofInstant(end, ZoneId.of("Asia/Tokyo"))));
  }

  @Test
  void givenInvalidRange_whenCreatingRangeTime_thenThrowException() {
    // given
    var start = LocalDateTime.of(2023, 1, 2, 0, 0).atZone(ZoneId.of("Asia/Tokyo")).toInstant();
    var end = LocalDateTime.of(2023, 1, 1, 0, 0).atZone(ZoneId.of("Asia/Tokyo")).toInstant();

    // when
    IllegalArgumentException exception =
        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class, () -> RangeTime.of(start, end));

    // then
    assertThat(exception.getMessage(), is("The start value is greater than the end value"));
  }
}
