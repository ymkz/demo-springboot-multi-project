package dev.ymkz.demo.api.presenter;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import jakarta.validation.ValidationException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<ProblemDetail> handleValidationException(ValidationException ex) {
    return ResponseEntity.of(ProblemDetail.forStatusAndDetail(
        BAD_REQUEST, ex.getMessage()))
        .build();
  }

  @ExceptionHandler
  public ResponseEntity<ProblemDetail> handleMyBatisException(MyBatisSystemException ex) {
    return ResponseEntity.of(ProblemDetail.forStatusAndDetail(
        INTERNAL_SERVER_ERROR, ex.getCause().getMessage()))
        .build();
  }

  @ExceptionHandler
  public ResponseEntity<ProblemDetail> handleOtherException(Exception ex) {
    return ResponseEntity.of(ProblemDetail.forStatusAndDetail(
        INTERNAL_SERVER_ERROR, ex.getMessage()))
        .build();
  }
}
