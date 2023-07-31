package com.coopeuch.challenge.controllers;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.coopeuch.challenge.domain.models.ServiceResponse;

public class BaseController {

  // Error MethodArgumentNotValid
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ServiceResponse<?>> handleValidationExceptions(MethodArgumentNotValidException e) {
    var errors = new ArrayList<String>();

    e.getBindingResult().getAllErrors().forEach((error) -> {
      var fieldName = ((FieldError) error).getField();
      var errorMessage = error.getDefaultMessage();
      errors.add(fieldName + ": " + errorMessage);
    });

    var response = new ServiceResponse<>(
        "ERROR",
        "Hubo un error al obtener el recurso",
        errors,
        null);

    return ResponseEntity.badRequest().body(response);
  }

  // Error general
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ServiceResponse<?>> handleException(Exception e) {
    var errors = new ArrayList<String>();

    errors.add(e.getMessage());

    var response = new ServiceResponse<>(
        "ERROR",
        "Hubo un error al obtener el recurso",
        errors,
        null);

    return ResponseEntity.badRequest().body(response);
  }
}
