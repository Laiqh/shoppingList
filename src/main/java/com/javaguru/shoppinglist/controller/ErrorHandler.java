package com.javaguru.shoppinglist.controller;

import com.javaguru.shoppinglist.domain.ApiError;
import com.javaguru.shoppinglist.service.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.NoSuchElementException;


@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError> handleValidationException(ValidationException ex) {
        ApiError error = new ApiError(new Date(), HttpStatus.BAD_REQUEST, ex.getClass().getSimpleName(), ex.getMessage());

        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiError> handleNoSuchElementException(NoSuchElementException ex) {
        ApiError error = new ApiError(new Date(), HttpStatus.NOT_FOUND, ex.getClass().getSimpleName(), ex.getMessage());

        return new ResponseEntity<>(error, error.getStatus());
    }
}
