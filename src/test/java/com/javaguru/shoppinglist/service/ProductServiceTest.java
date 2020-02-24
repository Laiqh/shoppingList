package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.Repository;
import com.javaguru.shoppinglist.service.validation.ValidationException;
import com.javaguru.shoppinglist.service.validation.ValidationRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ValidationRule<Product> validationRule;
    @Mock
    private Repository<Product> repository;
    @InjectMocks
    ProductService service;

    Product product;

    @BeforeEach
    public void init() {
        product = new Product();
    }

    @Test
    public void add() throws ValidationException {
        service.add(product);

        InOrder inOrder = inOrder(validationRule, repository);
        inOrder.verify(validationRule).validate(product);
        inOrder.verify(repository).insert(product);
    }

    @Test
    public void findById() {
        service.findById(0L);

        verify(repository).get(0L);
    }
}