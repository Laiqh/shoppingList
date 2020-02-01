package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class DiscountNotNull extends AbstractValidationRule<Product> {
    public void validate(Product product) throws ValidationException {
        if (product.getDiscount() == null) {
            throw new ValidationException("Discount can't be null");
        }
    }
}
