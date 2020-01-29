package com.javaguru.shoppinglist.validation;

public interface ValidationRule<T> {
    void validate(T item) throws ValidationException;
}
