package dev.ymkz.demo.core.domain.model;

import dev.ymkz.demo.core.domain.value.BookStatus;
import dev.ymkz.demo.core.domain.value.Isbn;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

public record Book(
    long id,
    Isbn isbn,
    @NotBlank String title,
    @Nullable @PositiveOrZero Integer price,
    BookStatus status,
    int authorId,
    @NotBlank String authorName,
    int publisherId,
    @NotBlank String publisherName,
    @Nullable LocalDateTime publishedAt,
    LocalDateTime createdAt,
    @Nullable LocalDateTime updatedAt,
    @Nullable LocalDateTime deletedAt) {}
