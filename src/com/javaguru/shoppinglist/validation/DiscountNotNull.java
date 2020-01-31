package com.javaguru.shoppinglist.validation;

import com.javaguru.shoppinglist.domain.Product;

public class DiscountNotNull implements ValidationRule<Product> {
    public void validate(Product product) throws ValidationException {
        if (product.getDiscount() == null) {
            throw new ValidationException("Discount can't be null");
        }
    }
}
