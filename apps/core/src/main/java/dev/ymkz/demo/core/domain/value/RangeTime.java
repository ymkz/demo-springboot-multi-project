package dev.ymkz.demo.core.domain.value;

import java.time.LocalDateTime;

public record RangeTime(LocalDateTime start, LocalDateTime end) {
  public RangeTime {
    if (start != null && end != null && start.isAfter(end)) {
      throw new IllegalArgumentException("The start value is greater than the end value");
    }
  }

  public static RangeTime of(LocalDateTime start, LocalDateTime end) {
    return new RangeTime(start != null ? start : null, end != null ? end : null);
  }
}
