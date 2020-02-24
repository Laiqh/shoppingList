package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DiscountLessThanOrEqualsToOneTest {
    private Product product;
    ValidationRule rule;
    BigDecimal discount;

    @BeforeEach
    public void init() {
        product = new Product();
        rule = new DiscountLessThanOrEqualsToOne();
    }

    @Test
    public void whenLessThanOne() {
        discount = new BigDecimal("0.5");
        product.setDiscount(discount);
        try {
            rule.validate(product);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void whenEqualsToOne() {
        discount = new BigDecimal("1");
        product.setDiscount(discount);
        try {
            rule.validate(product);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void whenGreaterThanOne() {
        discount = new BigDecimal("1.5");
        product.setDiscount(discount);
        Exception exception = assertThrows(ValidationException.class, () -> {
            rule.validate(product);
        });
        String expectedMessage = "Discount can't be greater than 1";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}