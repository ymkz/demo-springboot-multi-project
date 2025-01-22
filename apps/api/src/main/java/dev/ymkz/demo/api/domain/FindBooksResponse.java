package dev.ymkz.demo.api.domain;

import dev.ymkz.demo.core.domain.model.Book;
import dev.ymkz.demo.core.domain.value.BookStatus;
import dev.ymkz.demo.core.domain.value.Pagination;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(name = "FindBooksResponse")
public record FindBooksResponse(ResponsePagination pagination, List<Hit> hits) {

  record ResponsePagination(
      @Schema(example = "0") int offset,
      @Schema(example = "100") int limit,
      @Schema(example = "1") int total) {
  }

  record Hit(
      @Schema(example = "1") long id,
      @Schema(example = "9784873115658") String isbn,
      @Schema(example = "リーダブルコード") String title,
      @Schema(example = "2640") Integer price,
      @Schema(example = "PUBLISHED") BookStatus status,
      @Schema(example = "1") int authorId,
      @Schema(example = "Dustin Boswell") String authorName,
      @Schema(example = "1") int publisherId,
      @Schema(example = "O'Reilly") String publisherName) {

    private static Hit of(Book book) {
      return new Hit(
          book.id(),
          book.isbn().value(),
          book.title(),
          book.price(),
          book.status(),
          book.authorId(),
          book.authorName(),
          book.publisherId(),
          book.publisherName());
    }
  }

  public static FindBooksResponse of(Pagination<Book> data) {
    return new FindBooksResponse(
        new ResponsePagination(data.offset(), data.limit(), data.total()),
        data.content().stream().map(Hit::of).toList());
  }
}
