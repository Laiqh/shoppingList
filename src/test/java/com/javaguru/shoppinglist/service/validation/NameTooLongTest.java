package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTooLongTest {
    private static final int MAX_LENGTH = 5;
    private static final String NAME_ABOVE_MAX_LENGTH = "long name";
    private static final String NAME_MAX_LENGTH = "name1";
    private static final String NAME_BELOW_MAX_LENGTH = "name";

    private Product product;
    private ValidationRule rule;
    private String name;

    @BeforeEach
    public void init() {
        product = new Product();
        rule = new NameTooLong(MAX_LENGTH);
    }

    @Test
    public void whenAboveMaxLength() {
        name = NAME_ABOVE_MAX_LENGTH;
        product.setName(name);

        Exception exception = assertThrows(ValidationException.class, () -> {
            rule.validate(product);
        });
        String expectedMessage = "Name can not be longer than " + MAX_LENGTH;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void whenAtMaxLength() {
        name = NAME_MAX_LENGTH;
        product.setName(name);

        try {
            rule.validate(product);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void whenBelowMaxLength() {
        name = NAME_BELOW_MAX_LENGTH;
        product.setName(name);

        try {
            rule.validate(product);
        } catch (Exception e) {
            fail();
        }
    }
}