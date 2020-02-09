package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class NotNull extends AbstractValidationRule<Product> {
    public void validate(Product product) throws ValidationException {
        if (product == null) {
            throw new ValidationException("Product can not be null");
        }
    }
}
