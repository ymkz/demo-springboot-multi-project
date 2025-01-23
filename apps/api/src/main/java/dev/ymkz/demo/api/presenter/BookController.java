package dev.ymkz.demo.api.presenter;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import dev.ymkz.demo.api.domain.CreateBookBody;
import dev.ymkz.demo.api.domain.FindBookByIdResponse;
import dev.ymkz.demo.api.domain.FindBooksQueryParam;
import dev.ymkz.demo.api.domain.FindBooksResponse;
import dev.ymkz.demo.api.domain.UpdateBookBody;
import dev.ymkz.demo.api.usecase.BookSearchUsecase;
import dev.ymkz.demo.core.domain.model.BookSearchQuery;
import dev.ymkz.demo.core.domain.value.Isbn;
import dev.ymkz.demo.core.domain.value.RangeInteger;
import dev.ymkz.demo.core.domain.value.RangeTime;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@Tag(name = "Book")
@RequiredArgsConstructor
public class BookController {

  private final BookSearchUsecase bookSearchUsecase;

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  @Operation(operationId = "findBooks", summary = "書籍データを検索する")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400", description = "不正なリクエスト", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "500", description = "サーバーエラー", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
  })
  public FindBooksResponse findBooks(@ParameterObject FindBooksQueryParam queryParam) {

    var data = bookSearchUsecase.execute(
        new BookSearchQuery(
            Isbn.of(queryParam.isbn()),
            queryParam.title(),
            RangeInteger.of(queryParam.priceFrom(), queryParam.priceTo()),
            queryParam.status(),
            RangeTime.of(queryParam.publishedTimeStart(), queryParam.publishedTimeEnd()),
            queryParam.order(),
            queryParam.offset(),
            queryParam.limit()));

    return FindBooksResponse.of(data);
  }

  @GetMapping(value = "{id}", produces = APPLICATION_JSON_VALUE)
  @Operation(operationId = "findBookById", summary = "書籍データを取得する")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400", description = "不正なリクエスト", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "404", description = "存在しないリソース", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "500", description = "サーバーエラー", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
  })
  public FindBookByIdResponse findBookById(@PathVariable long id) {
    return null;
  }

  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  @Operation(operationId = "createBook", summary = "書籍データを作成する")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400", description = "不正なリクエスト", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "500", description = "サーバーエラー", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
  })
  public void createBook(@RequestBody CreateBookBody body) {
  }

  @PatchMapping(value = "{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  @Operation(operationId = "updateBook", summary = "書籍データを更新する")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400", description = "不正なリクエスト", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "404", description = "存在しないリソース", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "500", description = "サーバーエラー", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
  })
  public void updateBook(@PathVariable long id, @RequestBody UpdateBookBody body) {
  }

  @DeleteMapping(value = "{id}", produces = APPLICATION_JSON_VALUE)
  @Operation(operationId = "deleteBook", summary = "書籍データを削除する")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400", description = "不正なリクエスト", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "404", description = "存在しないリソース", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "500", description = "サーバーエラー", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
  })
  public void deleteBook(@PathVariable long id) {
  }
}
