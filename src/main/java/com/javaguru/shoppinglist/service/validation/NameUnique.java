package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NameUnique extends AbstractValidationRule<Product> {
    private ProductRepository productRepository;

    public NameUnique(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void validate(Product product) throws ValidationException {
        List<Product> products = productRepository.findAll();
        for (Product p : products) {
            if (p.getName().equals(product.getName()) && !p.getId().equals(product.getId())) {
                throw new ValidationException("Name must be unique");
            }
        }
    }
}
