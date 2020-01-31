package com.javaguru.shoppinglist.validation;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractValidationRuleList<T> implements ValidationRule<T> {
    private List<ValidationRule<T>> rules = new ArrayList<>();

    public void add(ValidationRule<T> rule) {
        rules.add(rule);
    }

    public void validate(T t) throws ValidationException {
        for (ValidationRule<T> rule : rules) {
            rule.validate(t);
        }
    }
}
