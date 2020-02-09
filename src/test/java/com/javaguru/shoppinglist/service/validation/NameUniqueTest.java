package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NameUniqueTest {
    private static final String NAME = "name";
    private static final String ANOTHER_NAME = "not name";

    private Product product;
    private Repository repository;
    private ValidationRule rule;
    private Product anotherProduct;

    @BeforeEach
    public void init() {
        product = new Product();
        product.setName(NAME);

        anotherProduct = new Product();
        anotherProduct.setName(ANOTHER_NAME);

        repository = Mockito.mock(Repository.class);
        rule = new NameUnique(repository);
    }

    @Test
    public void isUnique() {
        List<Product> uniqueList = new ArrayList<>(Arrays.asList(anotherProduct));
        Mockito.when(repository.getAll()).thenReturn(uniqueList);

        try {
            rule.validate(product);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void notUnique() {
        List<Product> notUniqueList = new ArrayList<>(Arrays.asList(anotherProduct, product));
        Mockito.when(repository.getAll()).thenReturn(notUniqueList);

        Exception exception = assertThrows(ValidationException.class, () -> {
            rule.validate(product);
        });
        String expectedMessage = "Name must be unique";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}