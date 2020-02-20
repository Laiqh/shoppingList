package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class ProductValidationRule extends AbstractValidationRuleList<Product> implements ValidationRule<Product> {
    @Autowired
    public ProductValidationRule(List<ValidationRule<Product>> rules) {
        super(rules);
    }

    public ProductValidationRule() {
        super();
    }
}
