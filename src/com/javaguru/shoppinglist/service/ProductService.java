package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.Repository;
import com.javaguru.shoppinglist.service.validation.ValidationException;
import com.javaguru.shoppinglist.service.validation.ValidationRule;

public class ProductService {
    private ValidationRule validationRule;
    private Repository repository;

    public ProductService(ValidationRule validationRule, Repository repository) {
        this.validationRule = validationRule;
        this.repository = repository;
    }

    public Long add(Product product) throws ValidationException {
        validationRule.validate(product);
        return repository.insert(product);
    }
}
