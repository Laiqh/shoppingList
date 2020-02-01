package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.Repository;

import java.util.List;

public class NameUnique extends AbstractValidationRule<Product> {
    private Repository<Product> repository;

    public NameUnique(Repository<Product> repository) {
        this.repository = repository;
    }

    public void validate(Product product) throws ValidationException {
        List<Product> products = repository.getAll();
        for (Product p : products) {
            if (p.getName().equals(product.getName())) {
                throw new ValidationException("Name must be unique");
            }
        }
    }
}
