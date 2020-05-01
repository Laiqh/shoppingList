package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.converter.ProductDTOConverter;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ValidationException;
import com.javaguru.shoppinglist.service.validation.ValidationRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
public class ProductService {
    private ValidationRule<Product> validationRule;
    private ProductRepository productRepository;
    private ProductDTOConverter converter;

    @Autowired
    public ProductService(ValidationRule<Product> rules, ProductRepository productRepository, ProductDTOConverter converter) {
        this.productRepository = productRepository;
        this.validationRule = rules;
        this.converter = converter;
    }

    public Long save(ProductDTO productDTO) throws ValidationException {
        Product product = converter.convert(productDTO);
        validationRule.validate(product);
        product = productRepository.save(product);
        return product.getId();
    }

    public void update(ProductDTO productDTO) throws ValidationException {
        productRepository.findById(productDTO.getId()).orElseThrow(() -> new NoSuchElementException("No record found for id " + productDTO.getId()));
        Product newProduct = converter.convert(productDTO);
        validationRule.validate(newProduct);
        productRepository.save(newProduct);
    }

    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No record found with id: " + id));

        return converter.convert(product);
    }

    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = products.stream().map(x -> converter.convert(x)).collect(Collectors.toList());

        return productDTOs;
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public ValidationRule<Product> getValidationRule() {
        return validationRule;
    }

    public void setValidationRule(ValidationRule<Product> validationRule) {
        this.validationRule = validationRule;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
