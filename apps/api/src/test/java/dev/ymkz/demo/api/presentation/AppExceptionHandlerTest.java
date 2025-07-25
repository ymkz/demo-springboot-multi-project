package dev.ymkz.demo.api.presentation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

class AppExceptionHandlerTest {

  private final AppExceptionHandler handler = new AppExceptionHandler();

  @Test
  void handleValidationException_ShouldReturnBadRequest() {
    var ex = new ValidationException("バリデーションエラー");
    ResponseEntity<ProblemDetail> response = handler.handleValidationException(ex);

    assertThat(response.getStatusCode()).isEqualTo(BAD_REQUEST);
  }

  @Test
  void handleMyBatisException_ShouldReturnInternalServerError() {
    var ex = new MyBatisSystemException("MyBatisエラー", new RuntimeException(""));
    ResponseEntity<ProblemDetail> response = handler.handleMyBatisException(ex);

    assertThat(response.getStatusCode()).isEqualTo(INTERNAL_SERVER_ERROR);
  }

  @Test
  void handleOtherException_ShouldReturnInternalServerError() {
    var ex = new RuntimeException("予期せぬエラー");
    ResponseEntity<ProblemDetail> response = handler.handleOtherException(ex);

    assertThat(response.getStatusCode()).isEqualTo(INTERNAL_SERVER_ERROR);
  }
}
