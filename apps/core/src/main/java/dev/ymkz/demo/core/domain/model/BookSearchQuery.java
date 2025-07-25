package dev.ymkz.demo.core.domain.model;

import dev.ymkz.demo.core.domain.valueobject.BookOrder;
import dev.ymkz.demo.core.domain.valueobject.BookStatus;
import dev.ymkz.demo.core.domain.valueobject.Isbn;
import dev.ymkz.demo.core.domain.valueobject.RangeInteger;
import dev.ymkz.demo.core.domain.valueobject.RangeTime;
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
