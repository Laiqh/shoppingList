package com.javaguru.shoppinglist.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class NameValidationRule implements ValidationRule<Product> {
    private List<ValidationRule> rules = new ArrayList<>();

    public NameValidationRule() {
        rules.add(new NotNull());
        rules.add(new NameNotNull());
        rules.add(new NameTooShort());
        rules.add(new NameTooLong());

    }

    public void validate(Product product) throws ValidationException {
        for (ValidationRule rule : rules) {
            rule.validate(product);
        }
    }

}
