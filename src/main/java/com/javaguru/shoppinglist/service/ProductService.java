package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.converter.ProductDTOConverter;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.repository.Repository;
import com.javaguru.shoppinglist.service.validation.ValidationException;
import com.javaguru.shoppinglist.service.validation.ValidationRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductService {
    private ValidationRule<Product> validationRule;
    private Repository<Product> repository;
    private ProductDTOConverter converter;

    @Autowired
    public ProductService(ValidationRule<Product> rules, Repository<Product> repository, ProductDTOConverter converter) {
        this.repository = repository;
        this.validationRule = rules;
        this.converter = converter;
    }

    public Long add(ProductDTO productDTO) throws ValidationException {
        Product product = converter.convert(productDTO);
        validationRule.validate(product);
        Long id = repository.insert(product);
        return id;
    }

    public void update(ProductDTO productDTO) throws ValidationException {
        Product product = converter.convert(productDTO);
        validationRule.validate(product);
        repository.update(product);
    }

    public ProductDTO findById(Long id) {
        Product product = repository.get(id);

        return converter.convert(product);
    }

    public List<ProductDTO> findAll() {
        List<Product> products = repository.getAll();
        List<ProductDTO> productDTOs = products.stream().map(x -> converter.convert(x)).collect(Collectors.toList());

        return productDTOs;
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
