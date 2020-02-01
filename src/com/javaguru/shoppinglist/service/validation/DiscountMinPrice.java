package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;

public class DiscountMinPrice extends AbstractValidationRule<Product> {
    private BigDecimal minPrice;

    public DiscountMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public void validate(Product product) throws ValidationException {
        if (product.getPrice().compareTo(minPrice) < 0 && product.getDiscount().compareTo(BigDecimal.ZERO) > 0) {
            throw new ValidationException("Can not set a discount for a price lower than " + minPrice);
        }
    }
}
