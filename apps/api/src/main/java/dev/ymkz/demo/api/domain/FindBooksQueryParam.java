package dev.ymkz.demo.api.domain;

import dev.ymkz.demo.core.domain.value.BookOrder;
import dev.ymkz.demo.core.domain.value.BookStatus;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

public record FindBooksQueryParam(
  @Parameter(description = "ISBN-13:完全一致", example = "9784873115658") String isbn,
  @Parameter(description = "書籍タイトル:部分一致") String title,
  @Parameter(description = "価格:下限") @Min(0) Integer priceFrom,
  @Parameter(description = "価格:上限") @Min(0) Integer priceTo,
  @Parameter(description = "ステータス") List<BookStatus> status,
  @Parameter(description = "出版日:開始", example = "2025-01-23T01:23:45.000Z") LocalDateTime publishedAtStart,
  @Parameter(description = "出版日:終了", example = "2025-01-23T01:23:45.000Z") LocalDateTime publishedAtEnd,
  @Parameter(description = "並び順") BookOrder order,
  @Parameter(description = "取得開始位置") @Min(0) Integer offset,
  @Parameter(description = "取得数") @Min(1) @Max(100) Integer limit
) {
  public FindBooksQueryParam {
    if (order == null) {
      order = BookOrder.PUBLISHED_AT_DESC;
    }
    if (offset == null) {
      offset = 0;
    }
    if (limit == null) {
      limit = 100;
    }
  }
}
