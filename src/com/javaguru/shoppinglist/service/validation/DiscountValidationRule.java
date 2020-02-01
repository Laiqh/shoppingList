package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;

public class DiscountValidationRule extends AbstractValidationRuleList<Product> implements ValidationRule<Product> {
    public DiscountValidationRule() {
        this.add(new DiscountNotNull());
        this.add(new DiscountLessThanOne());
        this.add(new DiscountGreaterThanZero());
        this.add(new DiscountMinPrice(new BigDecimal("20")));
    }
}
