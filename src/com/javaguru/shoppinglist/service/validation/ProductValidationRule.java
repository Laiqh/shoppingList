package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.Repository;

public class ProductValidationRule extends AbstractValidationRuleList<Product> implements ValidationRule<Product> {
    public ProductValidationRule(Repository<Product> repository) {
        this.add(new NameValidationRule(repository));
        this.add(new PriceValidationRule());
        this.add(new DiscountValidationRule());
    }
}
