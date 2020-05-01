package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NameUniqueTest {
    private static final String NAME = "name";
    private static final String ANOTHER_NAME = "not name";
    private static final Long ID = 1L;
    private static final Long ANOTHER_ID = 2L;

    @Mock
    private ProductRepository productRepository;
    private ValidationRule rule;
    private Product product;
    private Product anotherProduct;

    @BeforeEach
    public void init() {
        product = new Product();

        anotherProduct = new Product();

        rule = new NameUnique(productRepository);
    }

    @Test
    public void differentNameDifferentIdShouldValidate() {
        product.setName(NAME);
        product.setId(ID);
        anotherProduct.setName(ANOTHER_NAME);
        anotherProduct.setId(ANOTHER_ID);

        List<Product> result = Arrays.asList(anotherProduct);
        when(productRepository.findAll()).thenReturn(result);

        try {
            rule.validate(product);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void differentNameSameIdShouldValidate() {
        product.setName(NAME);
        product.setId(ID);
        anotherProduct.setName(ANOTHER_NAME);
        anotherProduct.setId(ID);

        List<Product> result = Arrays.asList(anotherProduct);
        when(productRepository.findAll()).thenReturn(result);

        try {
            rule.validate(product);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void sameNameDifferentIdShouldThrowValidationException() {
        product.setName(NAME);
        product.setId(ID);
        anotherProduct.setName(NAME);
        anotherProduct.setId(ANOTHER_ID);

        List<Product> result = Arrays.asList(anotherProduct);
        when(productRepository.findAll()).thenReturn(result);

        assertThrows(ValidationException.class, () -> rule.validate(product));
    }

    @Test
    public void sameNameSameIdShouldValidate() {
        product.setName(NAME);
        product.setId(ID);
        anotherProduct.setName(NAME);
        anotherProduct.setId(ID);

        List<Product> result = Arrays.asList(anotherProduct);
        when(productRepository.findAll()).thenReturn(result);

        try {
            rule.validate(product);
        } catch (Exception e) {
            fail();
        }
    }
}