package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;

public class DiscountLessThanOrEqualsToOne extends AbstractValidationRule<Product> {
    public void validate(Product product) throws ValidationException {
        if (product.getDiscount().compareTo(BigDecimal.ONE) == 1) {
            throw new ValidationException("Discount can't be greater than 1");
        }
    }
}
