package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DiscountNotNullTest {
    private Product product;
    ValidationRule rule;
    private BigDecimal discount;

    @BeforeEach
    public void init() {
        product = new Product();
        rule = new DiscountNotNull();
    }

    @Test
    public void whenIsNull() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            rule.validate(product);
        });
        String expectedMessage = "Discount can't be null";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void whenNotNull() {
        discount = new BigDecimal("0.1");
        product.setDiscount(discount);

        try {
            rule.validate(product);
        } catch (Exception e) {
            fail();
        }
    }
}