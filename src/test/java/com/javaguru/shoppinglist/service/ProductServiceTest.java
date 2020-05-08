package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.converter.ProductDTOConverter;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ValidationException;
import com.javaguru.shoppinglist.service.validation.ValidationRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProductServiceTest {
    @Mock
    private ValidationRule<Product> validationRule;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductDTOConverter converter;
    @InjectMocks
    ProductService service;

    Product product;
    ProductDTO productDTO;

    @BeforeEach
    public void init() {
        product = new Product();
        productDTO = new ProductDTO();

        product.setId(1L);

        productDTO.setId(1L);

        when(productRepository.save(product)).thenReturn(product);
        when(converter.convert(productDTO)).thenReturn(product);
        when(converter.convert(product)).thenReturn(productDTO);
    }

    @Test
    public void save_ShouldReturnId() throws ValidationException {
        doNothing().when(validationRule).validate(product);

        Long id = service.save(productDTO);

        assertEquals(productDTO.getId(), id);
    }

    @Test
    public void save_ShouldThrowValidationException() {
        doThrow(new ValidationException()).when(validationRule).validate(product);

        assertThrows(ValidationException.class, () -> service.save(productDTO));
        verify(productRepository, never()).save(product);
    }

    @Test
    public void update() {
        when(productRepository.findById(productDTO.getId())).thenReturn(Optional.of(product));
        service.update(productDTO);
        verify(productRepository).save(product);
    }

    @Test
    public void update_ShouldThrowValidationException() {
        when(productRepository.findById(productDTO.getId())).thenReturn(Optional.of(product));
        doThrow(new ValidationException()).when(validationRule).validate(product);

        assertThrows(ValidationException.class, () -> service.update(productDTO));
        verify(productRepository, never()).save(product);
    }

    @Test
    public void update_ShouldThrowNoSuchElementException() {
        when(productRepository.findById(productDTO.getId())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.update(productDTO));
        verify(productRepository, never()).save(product);
    }

    @Test
    public void findById_ShouldReturnProductDTO() {
        when(productRepository.findById(productDTO.getId())).thenReturn(Optional.of(product));

        assertEquals(productDTO, service.findById(productDTO.getId()));
    }

    @Test
    public void findById_ShouldThrowNoSuchElementException() {
        when(productRepository.findById(productDTO.getId())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.findById(productDTO.getId()));
    }

    @Test
    public void findByName_ShouldReturnProductDtoList() {
        given(productRepository.findByName("name")).willReturn(Collections.singletonList(product));

        assertEquals(Collections.singletonList(productDTO), service.findByName("name"));
    }

    @Test
    public void findAll_ShouldReturnProductDtoList() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product));

        assertEquals(Arrays.asList(productDTO), service.findAll());
    }

    @Test
    public void findAll_PageableShouldReturnProductDtoList() {
        Page page = new PageImpl(Collections.singletonList(product));
        given(productRepository.findAll(BDDMockito.any(Pageable.class))).willReturn(page);

        assertEquals(Collections.singletonList(productDTO), service.findAll(1, 1));
    }

    @Test
    public void delete() {
        service.delete(productDTO.getId());
        verify(productRepository).deleteById(productDTO.getId());
    }
}