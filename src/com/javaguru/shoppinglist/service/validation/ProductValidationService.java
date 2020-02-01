package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.Repository;

import java.math.BigDecimal;

public class ProductValidationService extends AbstractValidationRuleList<Product> {
    private Repository<Product> repository;

    public ProductValidationService(Repository repository) {
        this.repository = repository;
        createValidationRule();
    }

    private void createValidationRule() {
        this.add(new NotNull());
        this.add(createNameValidationRule());
        this.add(createPriceValidationRule());
        this.add(createDiscountValidationRule());
    }

    private ValidationRule<Product> createNameValidationRule() {
        ValidationRule<Product> rule = new ProductValidationRule();
        rule.add(new NameNotNull());
        rule.add(new NameTooShort(3));
        rule.add(new NameTooLong(32));
        rule.add(new NameUnique(repository));
        return rule;
    }

    private ValidationRule<Product> createPriceValidationRule() {
        ValidationRule<Product> rule = new ProductValidationRule();
        rule.add(new PriceNotNull());
        rule.add(new PriceGreaterThanZero());
        return rule;
    }

    private ValidationRule<Product> createDiscountValidationRule() {
        ValidationRule<Product> rule = new ProductValidationRule();
        rule.add(new DiscountNotNull());
        rule.add(new DiscountLessThanOne());
        rule.add(new DiscountGreaterThanZero());
        rule.add(new DiscountMinPrice(new BigDecimal("20")));
        return rule;
    }
}
