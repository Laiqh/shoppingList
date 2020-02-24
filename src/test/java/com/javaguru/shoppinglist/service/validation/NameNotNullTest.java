package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameNotNullTest {
    private Product product;
    private ValidationRule rule;
    private String name;

    @BeforeEach
    public void init() {
        product = new Product();
        rule = new NameNotNull();
    }

    @Test
    public void whenIsNull() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            rule.validate(product);
        });
        String expectedMessage = "Name can not be null";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void whenNotNull() {
        name = "name";
        product.setName(name);

        try {
            rule.validate(product);
        } catch (Exception e) {
            fail();
        }
    }
}