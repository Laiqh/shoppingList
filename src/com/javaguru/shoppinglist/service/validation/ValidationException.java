package com.javaguru.shoppinglist.service.validation;

public class ValidationException extends Exception {
    public ValidationException(String errorMessage) {
        super(errorMessage);
    }
}
