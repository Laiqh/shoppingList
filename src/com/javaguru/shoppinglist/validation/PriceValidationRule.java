package com.javaguru.shoppinglist.validation;

import com.javaguru.shoppinglist.domain.Product;

public class PriceValidationRule extends AbstractValidationRuleList<Product> implements ValidationRule<Product> {
    public PriceValidationRule() {
        this.add(new PriceNotNull());
        this.add(new PriceGreaterThanZero());
    }
}
