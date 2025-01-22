package dev.ymkz.demo.core.gateway.datasource;

import dev.ymkz.demo.core.domain.model.Book;
import dev.ymkz.demo.core.domain.value.BookStatus;
import dev.ymkz.demo.core.domain.value.Isbn;
import jakarta.annotation.Nullable;
import java.time.LocalDateTime;

public record BookEntity(
    long id,
    String isbn,
    String title,
    @Nullable Integer price,
    BookStatus status,
    int authorId,
    String authorName,
    int publisherId,
    String publisherName,
    @Nullable LocalDateTime publishedAt,
    LocalDateTime createdAt,
    @Nullable LocalDateTime updatedAt,
    @Nullable LocalDateTime deletedAt) {

  public static Book toBook(BookEntity entity) {
    return new Book(
        entity.id,
        new Isbn(entity.isbn),
        entity.title,
        entity.price,
        entity.status,
        entity.authorId,
        entity.authorName,
        entity.publisherId,
        entity.publisherName,
        entity.publishedAt,
        entity.createdAt,
        entity.updatedAt,
        entity.deletedAt);
  }
}
