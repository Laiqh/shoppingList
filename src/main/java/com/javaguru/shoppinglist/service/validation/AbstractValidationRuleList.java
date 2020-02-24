package com.javaguru.shoppinglist.service.validation;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractValidationRuleList<T> implements ValidationRule<T> {
    private List<ValidationRule<T>> rules;

    public AbstractValidationRuleList() {
        rules = new ArrayList<>();
    }

    public AbstractValidationRuleList(List<ValidationRule<T>> rules) {
        this.rules = rules;
    }

    public void add(ValidationRule<T> rule) {
        rules.add(rule);
    }

    public void validate(T t) throws ValidationException {
        for (ValidationRule<T> rule : rules) {
            rule.validate(t);
        }
    }
}
