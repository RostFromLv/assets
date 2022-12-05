package com.assets.exceptionhandler.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EntityExistsException.class)
  protected ResponseEntity<Object> handleEntityExistException(
      final EntityExistsException ex, final WebRequest request) {
    return handleInternal(ex, HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<Object> handleEntityNotFoundException(
      final EntityNotFoundException ex, final WebRequest request) {
    return handleInternal(ex, HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  protected ResponseEntity<Object> handleIllegalArgumentException(
      final IllegalArgumentException ex, final WebRequest request) {
    return handleInternal(ex, HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler({ConstraintViolationException.class})
  protected ResponseEntity<Object> handleConstraintViolationException(
      final ConstraintViolationException ex, final WebRequest request) {
    return handleInternal(ex, HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(NullPointerException.class)
  protected ResponseEntity<Object> handleNullPointerException(
      final NullPointerException ex, final WebRequest request) {
    return handleInternal(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  private ResponseEntity<Object> handleInternal(java.lang.Exception ex, HttpStatus status, WebRequest request) {
    return this.handleExceptionInternal(ex, null, copyHeaders(request), status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    List<ValidationFieldException> validationFieldErrorList = new ArrayList<>();

    for (FieldError exception : ex.getBindingResult().getFieldErrors()) {
      ValidationFieldException fieldException = new ValidationFieldException();
      fieldException.setException(exception.getDefaultMessage());
      fieldException.setField(exception.getField());
      validationFieldErrorList.add(fieldException);
    }

    ValidationException validationException = new ValidationException();
    validationException.setValidationException(validationFieldErrorList);
    validationException.setTimestamp(LocalDateTime.now());
    validationException.setStatus(status);
    validationException.setMessage(ex.getMessage());
    return new ResponseEntity<>(validationException, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      java.lang.Exception ex,
      Object body,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    Exception exception = new Exception();
    exception.setStatus(status);
    exception.setTimestamp(LocalDateTime.now());
    exception.setMessage(ex.getMessage());

    return new ResponseEntity<>(exception, new HttpHeaders(), status);
  }

  private HttpHeaders copyHeaders(final WebRequest request) {
    HttpHeaders headers = new HttpHeaders();
    Iterator<String> iterator = request.getHeaderNames();
    while (iterator.hasNext()) {
      String headerName = iterator.next();
      String headerValue = request.getHeader(headerName);
      headers.add(headerName, headerValue);
    }
    return headers;
  }
}
