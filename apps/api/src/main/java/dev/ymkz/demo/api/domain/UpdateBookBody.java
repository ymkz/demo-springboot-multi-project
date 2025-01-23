package dev.ymkz.demo.api.domain;

import dev.ymkz.demo.core.domain.value.BookStatus;
import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateBookBody(@Schema(example = "9784873115658") String isbn,
    @Schema(example = "リーダブルコード") String title,
    @Schema(example = "2640") Integer price,
    @Schema(example = "PUBLISHED") BookStatus status) {

}
