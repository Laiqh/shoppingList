package com.javaguru.shoppinglist.service.validation;

public interface ValidationRule<T> {
    void validate(T item) throws ValidationException;

    void add(ValidationRule<T> rule);
}
