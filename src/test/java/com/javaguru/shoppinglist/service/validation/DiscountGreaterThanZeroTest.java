package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiscountGreaterThanZeroTest {

    @Test
    public void lessThanZeroTest() {
        ValidationRule rule = new DiscountGreaterThanZero();
        Product product = new Product();
        product.setDiscount(new BigDecimal("-1"));
        Exception exception = assertThrows(ValidationException.class, () -> {
            rule.validate(product);
        });
        String expectedMessage = "Discount can't be less than zero";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}