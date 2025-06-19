package com.uncode.stop.rest_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.uncode.stop.rest_api.error.NotFoundException;
import com.uncode.stop.rest_api.error.ServiceException;

@ControllerAdvice
@ResponseBody
public class RestResponseEntityExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String handleNotFoundException(NotFoundException e) {
    return "Resource not found";
  }

  @ExceptionHandler(ServiceException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public String handleServiceException(ServiceException e) {
    return e.getMessage();
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public String handleException(Exception e) {
    return "Internal server error";
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
    return e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
  }
}
