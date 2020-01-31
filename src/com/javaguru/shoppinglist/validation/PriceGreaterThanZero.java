package com.javaguru.shoppinglist.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;

public class PriceGreaterThanZero implements ValidationRule<Product> {
    public void validate(Product product) throws ValidationException {
        if (product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Price must be greater than zero");
        }
    }
}
