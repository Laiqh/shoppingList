package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DiscountMinPriceTest {
    private Product product;
    private BigDecimal discount;
    private BigDecimal price;
    ValidationRule rule;

    private final BigDecimal MIN_PRICE = new BigDecimal("20");
    private final BigDecimal BELOW_MIN_PRICE = new BigDecimal("15");
    private final BigDecimal ABOVE_MIN_PRICE = new BigDecimal("25");

    @BeforeEach
    public void init() {
        product = new Product();
        rule = new DiscountMinPrice(MIN_PRICE);
    }

    @Test
    public void whenPriceBelowMinNonZeroDiscount() {
        discount = new BigDecimal("0.3");
        price = BELOW_MIN_PRICE;
        product.setDiscount(discount);
        product.setPrice(price);

        Exception exception = assertThrows(ValidationException.class, () -> {
            rule.validate(product);
        });
        String expectedMessage = "Can not set a discount for a price lower than " + MIN_PRICE;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void whenPriceBelowMinZeroDiscount() {
        discount = new BigDecimal("0");
        price = BELOW_MIN_PRICE;
        product.setDiscount(discount);
        product.setPrice(price);

        try {
            rule.validate(product);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void whenPriceAboveMinNonZeroDiscount() {
        discount = new BigDecimal("0.3");
        price = ABOVE_MIN_PRICE;
        product.setDiscount(discount);
        product.setPrice(price);

        try {
            rule.validate(product);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void whenPriceAboveMinZeroDiscount() {
        discount = new BigDecimal("0");
        price = ABOVE_MIN_PRICE;
        product.setDiscount(discount);
        product.setPrice(price);

        try {
            rule.validate(product);
        } catch (Exception e) {
            fail();
        }
    }

}