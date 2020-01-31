package com.javaguru.shoppinglist.validation;

import com.javaguru.shoppinglist.domain.Product;

public class ProductValidationRule extends AbstractValidationRuleList<Product> implements ValidationRule<Product> {
    public ProductValidationRule() {
        this.add(new NameValidationRule());
        this.add(new PriceValidationRule());
        this.add(new DiscountValidationRule());
    }
}
