package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTooShortTest {
    private static final int MIN_LENGTH = 3;
    private static final String NAME_BELOW_MIN_LENGTH = "gg";
    private static final String NAME_AT_MIN_LENGTH = "ggg";
    private static final String NAME_ABOVE_MIN_LENGTH = "full name";

    private Product product;
    private String name;
    private ValidationRule rule;

    @BeforeEach
    public void init() {
        product = new Product();
        rule = new NameTooShort(MIN_LENGTH);
    }

    @Test
    public void whenBelowMinLength() {
        name = NAME_BELOW_MIN_LENGTH;
        product.setName(name);

        Exception exception = assertThrows(ValidationException.class, () -> {
            rule.validate(product);
        });
        String expectedMessage = "Name can not be shorter than " + MIN_LENGTH;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void whenAtMinLength() {
        name = NAME_AT_MIN_LENGTH;
        product.setName(name);

        try {
            rule.validate(product);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void whenAboveMinLength() {
        name = NAME_ABOVE_MIN_LENGTH;
        product.setName(name);

        try {
            rule.validate(product);
        } catch (Exception e) {
            fail();
        }
    }
}