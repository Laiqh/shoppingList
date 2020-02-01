package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class NameTooShort implements ValidationRule<Product> {
    private int minLength;

    public NameTooShort(int minLength) {
        this.minLength = minLength;
    }

    public void validate(Product product) throws ValidationException {
        if (product.getName().length() < minLength) {
            throw new ValidationException("Name can not be shorter than " + minLength);
        }
    }
}
