package com.javaguru.shoppinglist.controller;

import com.javaguru.shoppinglist.domain.ApiError;
import com.javaguru.shoppinglist.service.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;


@RestControllerAdvice
public class ErrorHandler {

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> details = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            details.add(error.getField() + ": " + error.getDefaultMessage());
        }

        ApiError error = new ApiError(new Date(), HttpStatus.BAD_REQUEST, ex.getClass().getSimpleName(), details);

        return new ResponseEntity<>(error, error.getStatus());
    }
}
