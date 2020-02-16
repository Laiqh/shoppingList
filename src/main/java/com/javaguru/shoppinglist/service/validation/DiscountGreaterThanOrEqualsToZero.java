package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DiscountGreaterThanOrEqualsToZero extends AbstractValidationRule<Product> {
    public void validate(Product product) throws ValidationException {
        if (product.getDiscount().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("Discount can't be less than zero");
        }
    }
}
