package dev.ymkz.demo.core.domain.model;

import dev.ymkz.demo.core.domain.value.BookOrder;
import dev.ymkz.demo.core.domain.value.BookStatus;
import dev.ymkz.demo.core.domain.value.Isbn;
import dev.ymkz.demo.core.domain.value.RangeInteger;
import dev.ymkz.demo.core.domain.value.RangeTime;
import java.util.List;

public record BookSearchQuery(
  Isbn isbn,
  String title,
  RangeInteger priceRange,
  List<BookStatus> statuses,
  RangeTime publishedAtRange,
  BookOrder order,
  Integer offset,
  Integer limit
) {}
