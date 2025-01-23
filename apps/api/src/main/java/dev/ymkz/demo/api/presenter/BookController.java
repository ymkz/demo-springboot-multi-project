package dev.ymkz.demo.api.presenter;

import dev.ymkz.demo.api.domain.FindBooksQueryParam;
import dev.ymkz.demo.api.domain.FindBooksResponse;
import dev.ymkz.demo.api.usecase.BookSearchUsecase;
import dev.ymkz.demo.core.domain.model.BookSearchQuery;
import dev.ymkz.demo.core.domain.value.Isbn;
import dev.ymkz.demo.core.domain.value.RangeInteger;
import dev.ymkz.demo.core.domain.value.RangeTime;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@Tag(name = "Book")
@RequiredArgsConstructor
public class BookController {

  private final BookSearchUsecase bookSearchUsecase;

  @GetMapping
  @Operation(operationId = "findBooks", summary = "書籍データを検索する")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400", description = "不正なリクエスト"),
      @ApiResponse(responseCode = "500", description = "サーバーエラー")
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

}
