package dev.ymkz.demo.core.domain.value;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public record RangeTime(LocalDateTime start, LocalDateTime end) {

  public RangeTime {
    if (start != null && end != null && start.isAfter(end)) {
      throw new IllegalArgumentException("The start value is greater than the end value");
    }
  }

  public static RangeTime of(Instant start, Instant end) {
    return new RangeTime(
        start != null ? LocalDateTime.ofInstant(start, ZoneId.of("Asia/Tokyo")) : null,
        end != null ? LocalDateTime.ofInstant(end, ZoneId.of("Asia/Tokyo")) : null);
  }
}
