package dev.ymkz.demo.api.presentation.controller;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import dev.ymkz.demo.api.application.usecase.BookDownloadUsecase;
import dev.ymkz.demo.api.application.usecase.BookSearchUsecase;
import dev.ymkz.demo.api.presentation.dto.CreateBookBody;
import dev.ymkz.demo.api.presentation.dto.FindBookByIdResponse;
import dev.ymkz.demo.api.presentation.dto.SearchBooksQueryParam;
import dev.ymkz.demo.api.presentation.dto.SearchBooksResponse;
import dev.ymkz.demo.api.presentation.dto.UpdateBookBody;
import dev.ymkz.demo.core.domain.model.BookSearchQuery;
import dev.ymkz.demo.core.domain.valueobject.Isbn;
import dev.ymkz.demo.core.domain.valueobject.RangeInteger;
import dev.ymkz.demo.core.domain.valueobject.RangeTime;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@Tag(name = "Book")
@RequiredArgsConstructor
public class BookController {

    private final BookSearchUsecase bookSearchUsecase;
    private final BookDownloadUsecase bookDownloadUsecase;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(operationId = "searchBooks")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "成功"),
                @ApiResponse(
                        responseCode = "400",
                        description = "不正なリクエスト",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
                @ApiResponse(
                        responseCode = "500",
                        description = "アプリケーションエラー",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            })
    public SearchBooksResponse searchBooks(
            @ParameterObject SearchBooksQueryParam queryParam,
            @Parameter(description = "取得置") @Schema(defaultValue = "0") @Min(0) Integer offset,
            @Parameter(description = "取得数") @Schema(defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        var data = bookSearchUsecase.execute(new BookSearchQuery(
                Isbn.of(queryParam.isbn()),
                queryParam.title(),
                RangeInteger.of(queryParam.priceFrom(), queryParam.priceTo()),
                queryParam.status(),
                RangeTime.of(queryParam.publishedAtStart(), queryParam.publishedAtEnd()),
                queryParam.order(),
                offset == null ? 0 : offset,
                limit == null ? 20 : limit));

        return SearchBooksResponse.of(data);
    }

    @GetMapping(path = "download", produces = "text/csv")
    @Operation(operationId = "downloadBooks")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "成功"),
                @ApiResponse(
                        responseCode = "400",
                        description = "不正なリクエスト",
                        content =
                                @Content(
                                        schema = @Schema(implementation = ProblemDetail.class),
                                        mediaType = APPLICATION_JSON_VALUE)),
                @ApiResponse(
                        responseCode = "500",
                        description = "アプリケーションエラー",
                        content =
                                @Content(
                                        schema = @Schema(implementation = ProblemDetail.class),
                                        mediaType = APPLICATION_JSON_VALUE)),
            })
    public ResponseEntity<byte[]> downloadBooks(@ParameterObject SearchBooksQueryParam queryParam) {
        var data = bookDownloadUsecase.execute(new BookSearchQuery(
                Isbn.of(queryParam.isbn()),
                queryParam.title(),
                RangeInteger.of(queryParam.priceFrom(), queryParam.priceTo()),
                queryParam.status(),
                RangeTime.of(queryParam.publishedAtStart(), queryParam.publishedAtEnd()),
                queryParam.order(),
                null,
                null));

        final var MEDIA_TYPE_TEXT_CSV = new MediaType("text", "csv");
        final var CONTENT_DISPOSITION_VALUE = "attachment; filename=download.csv";

        return ResponseEntity.ok()
                .header(CONTENT_DISPOSITION, CONTENT_DISPOSITION_VALUE)
                .contentType(MEDIA_TYPE_TEXT_CSV)
                .body(data);
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(operationId = "findBookById")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "成功"),
                @ApiResponse(
                        responseCode = "400",
                        description = "不正なリクエスト",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
                @ApiResponse(
                        responseCode = "404",
                        description = "存在しないリソース",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
                @ApiResponse(
                        responseCode = "500",
                        description = "アプリケーションエラー",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            })
    public FindBookByIdResponse findBookById(@PathVariable long id) {
        return FindBookByIdResponse.of(null);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(operationId = "createBook")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "201", description = "成功"),
                @ApiResponse(
                        responseCode = "400",
                        description = "不正なリクエスト",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
                @ApiResponse(
                        responseCode = "500",
                        description = "アプリケーションエラー",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            })
    public void createBook(@Validated @RequestBody CreateBookBody body) {}

    @PatchMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @Operation(operationId = "updateBook")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "成功"),
                @ApiResponse(
                        responseCode = "400",
                        description = "不正なリクエスト",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
                @ApiResponse(
                        responseCode = "404",
                        description = "存在しないリソース",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
                @ApiResponse(
                        responseCode = "500",
                        description = "アプリケーションエラー",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            })
    public void updateBook(@PathVariable long id, @RequestBody UpdateBookBody body) {}

    @DeleteMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(operationId = "deleteBook")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "204", description = "成功"),
                @ApiResponse(
                        responseCode = "400",
                        description = "不正なリクエスト",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
                @ApiResponse(
                        responseCode = "404",
                        description = "存在しないリソース",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
                @ApiResponse(
                        responseCode = "500",
                        description = "アプリケーションエラー",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            })
    public void deleteBook(@PathVariable long id) {}
}
