package com.javaguru.shoppinglist.validation;

import com.javaguru.shoppinglist.domain.Product;

public class PriceNotNull implements ValidationRule<Product> {
    public void validate(Product product) throws ValidationException {
        if (product.getPrice() == null) {
            throw new ValidationException("Price can't be null");
        }
    }
}
