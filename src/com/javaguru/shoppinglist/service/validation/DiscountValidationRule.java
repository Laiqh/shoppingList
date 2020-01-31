package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class DiscountValidationRule extends AbstractValidationRuleList<Product> implements ValidationRule<Product> {
    public DiscountValidationRule() {
        this.add(new DiscountNotNull());
        this.add(new DiscountLessThanOne());
    }
}
