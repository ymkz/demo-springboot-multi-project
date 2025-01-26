package dev.ymkz.demo.api.domain;

import dev.ymkz.demo.core.domain.model.Book;
import dev.ymkz.demo.core.domain.value.BookStatus;
import dev.ymkz.demo.core.domain.value.Pagination;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

@Schema(name = "FindBooksResponse")
public record FindBooksResponse(ResponsePagination pagination, List<ResponseHit> hits) {
  record ResponsePagination(
    @Schema(description = "取得開始位置", example = "0") int offset,
    @Schema(description = "取得数", example = "100") int limit,
    @Schema(description = "検索ヒット総数", example = "1") int total
  ) {}

  record ResponseHit(
    @Schema(description = "書籍ID", example = "1") long id,
    @Schema(description = "ISBN-13", example = "9784873115658") String isbn,
    @Schema(description = "タイトル", example = "リーダブルコード") String title,
    @Schema(description = "価格", example = "2640") Integer price,
    @Schema(description = "ステータス", example = "PUBLISHED") BookStatus status,
    @Schema(description = "出版日時:ISO8601", example = "2025-01-23T01:23:45.000Z") LocalDateTime publishedAt,
    @Schema(description = "著者ID", example = "1") int authorId,
    @Schema(description = "著者名", example = "Dustin Boswell") String authorName,
    @Schema(description = "出版社ID", example = "1") int publisherId,
    @Schema(description = "出版社名", example = "O'Reilly") String publisherName
  ) {
    private static ResponseHit of(Book book) {
      return new ResponseHit(
        book.id(),
        book.isbn().value(),
        book.title(),
        book.price(),
        book.status(),
        book.publishedAt(),
        book.authorId(),
        book.authorName(),
        book.publisherId(),
        book.publisherName()
      );
    }
  }

  public static FindBooksResponse of(Pagination<Book> data) {
    return new FindBooksResponse(
      new ResponsePagination(data.offset(), data.limit(), data.total()),
      data.content().stream().map(ResponseHit::of).toList()
    );
  }
}
