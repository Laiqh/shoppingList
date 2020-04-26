package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NameUniqueTest {
    private static final String NAME = "name";
    private static final String ANOTHER_NAME = "not name";

    @Mock
    private ProductRepository productRepository;
    private ValidationRule rule;
    private Product product;
    private Product anotherProduct;

    @BeforeEach
    public void init() {
        product = new Product();
        product.setName(NAME);

        anotherProduct = new Product();
        anotherProduct.setName(ANOTHER_NAME);

        rule = new NameUnique(productRepository);
    }

    @Test
    public void isUnique() {
        List<Product> uniqueList = new ArrayList<>(Arrays.asList(anotherProduct));
        when(productRepository.findAll()).thenReturn(uniqueList);

        try {
            rule.validate(product);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void notUnique() {
        List<Product> notUniqueList = new ArrayList<>(Arrays.asList(anotherProduct, product));
        when(productRepository.findAll()).thenReturn(notUniqueList);

        Exception exception = assertThrows(ValidationException.class, () -> {
            rule.validate(product);
        });
        String expectedMessage = "Name must be unique";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}