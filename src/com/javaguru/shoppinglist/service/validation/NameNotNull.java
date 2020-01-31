package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class NameNotNull implements ValidationRule<Product> {
    public void validate(Product product) throws ValidationException {
        if (product.getName() == null) {
            throw new ValidationException("Name is null");
        }
    }
}
