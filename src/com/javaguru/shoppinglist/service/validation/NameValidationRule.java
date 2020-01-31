package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class NameValidationRule extends AbstractValidationRuleList<Product> implements ValidationRule<Product> {

    public NameValidationRule() {
        this.add(new NotNull());
        this.add(new NameNotNull());
        this.add(new NameTooShort());
        this.add(new NameTooLong());

    }
}
