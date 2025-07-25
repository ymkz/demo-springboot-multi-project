package dev.ymkz.demo.core.domain.valueobject;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;

public record Pagination<T>(
  List<T> content,
  @PositiveOrZero int total,
  @Min(0) int offset,
  @Min(1) @Max(100) int limit
) {}
