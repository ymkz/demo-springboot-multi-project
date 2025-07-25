package dev.ymkz.demo.api.presentation.dto;

import dev.ymkz.demo.core.domain.model.Book;
import dev.ymkz.demo.core.domain.valueobject.BookStatus;
import dev.ymkz.demo.core.domain.valueobject.Pagination;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Schema(name = "SearchBooksResponse")
public record SearchBooksResponse(ResponsePagination pagination, List<ResponseHit> hits) {
  record ResponsePagination(
    @Schema(description = "取得開始位置", example = "0") @NotNull int offset,
    @Schema(description = "取得数", example = "100") @NotNull int limit,
    @Schema(description = "検索ヒット総数", example = "1") @NotNull int total
  ) {}

  record ResponseHit(
    @Schema(description = "書籍ID", example = "1") @NotNull long id,
    @Schema(description = "ISBN-13", example = "9784873115658") @NotNull String isbn,
    @Schema(description = "タイトル", example = "リーダブルコード") @NotNull String title,
    @Schema(description = "価格", example = "2640") Integer price,
    @Schema(description = "ステータス", example = "PUBLISHED") @NotNull BookStatus status,
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

  public static SearchBooksResponse of(Pagination<Book> data) {
    return new SearchBooksResponse(
      new ResponsePagination(data.offset(), data.limit(), data.total()),
      data.content().stream().map(ResponseHit::of).toList()
    );
  }
}
