package repository_spring.repository_spring.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import repository_spring.repository_spring.rest.ApiErrors;
import repository_spring.repository_spring.service.order.exceptions.ClientNotFoundException;
import repository_spring.repository_spring.service.order.exceptions.OrderNotFoundException;
import repository_spring.repository_spring.service.order.exceptions.ProductNotFoundException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

  @ExceptionHandler(ProductNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiErrors handleProductNotFoundException(ProductNotFoundException ex) {
    final String errorMessage = ex.getMessage();
    return new ApiErrors(errorMessage);
  }

  @ExceptionHandler(ClientNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiErrors handleClientNotFoundException(ClientNotFoundException ex) {
    final String errorMessage = ex.getMessage();
    return new ApiErrors(errorMessage);
  }

  @ExceptionHandler(OrderNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiErrors handleOrderNotFoundException(OrderNotFoundException ex) {
    final String errorMessage = ex.getMessage();
    return new ApiErrors(errorMessage);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiErrors handleAnyException(Exception ex) {
    final String errorMessage = ex.getMessage();
    return new ApiErrors(errorMessage);
  }
}
