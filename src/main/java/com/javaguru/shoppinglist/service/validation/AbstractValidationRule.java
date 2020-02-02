package com.javaguru.shoppinglist.service.validation;

public abstract class AbstractValidationRule<T> implements ValidationRule<T> {
    public void add(ValidationRule<T> rule) {
        throw new UnsupportedOperationException();
    }
}
