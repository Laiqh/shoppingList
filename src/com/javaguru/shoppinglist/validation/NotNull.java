package com.javaguru.shoppinglist.validation;

import com.javaguru.shoppinglist.domain.Product;

public class NotNull implements ValidationRule<Product> {
    public void validate(Product product) throws ValidationException {
        if (product == null) {
            throw new ValidationException("Product is null");
        }
    }
}
