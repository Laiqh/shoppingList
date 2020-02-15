package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.Repository;
import com.javaguru.shoppinglist.service.validation.ValidationException;
import com.javaguru.shoppinglist.service.validation.ValidationRule;

public class ProductService {
    private ValidationRule<Product> validationRule;
    private Repository<Product> repository;

    public ProductService(ValidationRule rules, Repository repository) {
        this.repository = repository;
        this.validationRule = rules;
    }

    public Long add(Product product) throws ValidationException {
        validationRule.validate(product);
        Long id = repository.insert(product);
        return id;
    }

    public Product findById(Long id) {
        return repository.get(id);
    }

    public ValidationRule<Product> getValidationRule() {
        return validationRule;
    }

    public void setValidationRule(ValidationRule<Product> validationRule) {
        this.validationRule = validationRule;
    }

    public Repository<Product> getRepository() {
        return repository;
    }

    public void setRepository(Repository<Product> repository) {
        this.repository = repository;
    }
}
