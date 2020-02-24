package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotNullTest {
    private Product product;
    private ValidationRule rule;

    @BeforeEach
    public void init() {
        rule = new NotNull();
    }

    @Test
    public void whenIsNull() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            rule.validate(product);
        });
        String expectedMessage = "Product can not be null";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void whenNotNull() {
        product = new Product();

        try {
            rule.validate(product);
        } catch (Exception e) {
            fail();
        }
    }
}