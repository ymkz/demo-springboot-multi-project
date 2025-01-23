package dev.ymkz.demo.api.domain;

import dev.ymkz.demo.core.domain.value.BookStatus;
import io.swagger.v3.oas.annotations.media.Schema;

public record FindBookByIdResponse(
    @Schema(example = "1") long id,
    @Schema(example = "9784873115658") String isbn,
    @Schema(example = "リーダブルコード") String title,
    @Schema(example = "2640") Integer price,
    @Schema(example = "PUBLISHED") BookStatus status,
    @Schema(example = "1") int authorId,
    @Schema(example = "Dustin Boswell") String authorName,
    @Schema(example = "1") int publisherId,
    @Schema(example = "O'Reilly") String publisherName) {

}
