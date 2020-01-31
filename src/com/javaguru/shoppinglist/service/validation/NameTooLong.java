package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class NameTooLong implements ValidationRule<Product> {
    private int maxLength = 32;

    public NameTooLong(int maxLength) {
        this.maxLength = maxLength;
    }

    public NameTooLong() {
        maxLength = 32;
    }

    public void validate(Product product) throws ValidationException {
        if (product.getName().length() > maxLength) {
            throw new ValidationException("Name can not be longer than " + maxLength);
        }
    }
}
