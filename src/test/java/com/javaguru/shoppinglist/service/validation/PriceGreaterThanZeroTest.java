package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PriceGreaterThanZeroTest {
    private static final BigDecimal PRICE_ABOVE_ZERO = new BigDecimal("1");
    private static final BigDecimal PRICE_AT_ZERO = BigDecimal.ZERO;
    private static final BigDecimal PRICE_BELOW_ZERO = new BigDecimal("-1");

    private Product product;
    private BigDecimal price;
    private ValidationRule rule;

    @BeforeEach
    public void init() {
        product = new Product();
        rule = new PriceGreaterThanZero();
    }

    @Test
    public void whenAboveZero() {
        price = PRICE_ABOVE_ZERO;
        product.setPrice(price);

        try {
            rule.validate(product);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void whenAtZero() {
        price = PRICE_AT_ZERO;
        product.setPrice(price);

        Exception exception = assertThrows(ValidationException.class, () -> {
            rule.validate(product);
        });
        String expectedMessage = "Price must be greater than zero";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void whenBelowZero() {
        price = PRICE_BELOW_ZERO;
        product.setPrice(price);

        Exception exception = assertThrows(ValidationException.class, () -> {
            rule.validate(product);
        });
        String expectedMessage = "Price must be greater than zero";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}