package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.Repository;
import com.javaguru.shoppinglist.service.validation.ValidationException;
import com.javaguru.shoppinglist.service.validation.ValidationRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    private ValidationRule<Product> validationRule;
    private Repository<Product> repository;

    @Autowired
    public ProductService(ValidationRule<Product> rules, Repository<Product> repository) {
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

    public List<Product> findAll() {
        return repository.getAll();
    }

    public void remove(Long id) {
        repository.remove(id);
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
