package repository_spring.repository_spring.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiErrors handleAnyException(MethodArgumentNotValidException ex) {
    List<String> errorMessages = ex
      .getBindingResult()
      .getAllErrors()
      .stream()
      .map(error -> error.getDefaultMessage())
      .collect(Collectors.toList());
    return new ApiErrors(errorMessages);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiErrors handleAnyException(Exception ex) {
    return new ApiErrors("server error");
  }
}
