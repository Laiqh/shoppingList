package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.Repository;

public class NameValidationRule extends AbstractValidationRuleList<Product> implements ValidationRule<Product> {

    public NameValidationRule(Repository<Product> repository) {
        this.add(new NotNull());
        this.add(new NameNotNull());
        this.add(new NameTooShort(3));
        this.add(new NameTooLong(32));
        this.add(new NameUnique(repository));

    }
}
