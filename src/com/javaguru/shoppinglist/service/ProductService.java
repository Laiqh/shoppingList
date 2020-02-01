package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.Repository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import com.javaguru.shoppinglist.service.validation.ValidationException;
import com.javaguru.shoppinglist.service.validation.ValidationRule;

public class ProductService {
    private ValidationRule<Product> validationRule;
    private Repository<Product> repository;

    public ProductService(Repository repository) {
        this.repository = repository;
        this.validationRule = new ProductValidationService(repository);
    }

    public Long add(Product product) throws ValidationException {
        validationRule.validate(product);
        return repository.insert(product);
    }

    public Product findById(Long id) {
        return repository.get(id);
    }
}
