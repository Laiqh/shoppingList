package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountGreaterThanOrEqualsToZeroTest {
    private Product product;
    ValidationRule rule;
    BigDecimal discount;

    @BeforeEach
    public void init() {
        product = new Product();
        rule = new DiscountGreaterThanOrEqualsToZero();
    }

    @Test
    public void whenLessThanZero() {
        discount = new BigDecimal("-0.5");
        product.setDiscount(discount);
        Exception exception = assertThrows(ValidationException.class, () -> {
            rule.validate(product);
        });
        String expectedMessage = "Discount can't be less than zero";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void whenEqualsZero() {
        discount = new BigDecimal("0");
        product.setDiscount(discount);
        try {
            rule.validate(product);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void whenGreaterThanZero() {
        discount = new BigDecimal("0.5");
        product.setDiscount(discount);
        try {
            rule.validate(product);
        } catch (Exception e) {
            fail();
        }
    }
}