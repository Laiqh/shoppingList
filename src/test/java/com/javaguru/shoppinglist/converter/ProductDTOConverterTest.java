package com.javaguru.shoppinglist.converter;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductDTOConverterTest {
    private ProductDTOConverter converter = new ProductDTOConverter();

    @Test
    public void convertProductToDto() {
        Product product = new Product();
        product.setId(1L);
        product.setName("name");
        product.setCategory("category");
        product.setPrice(BigDecimal.valueOf(1.0));
        product.setDiscount(BigDecimal.valueOf(0.5));
        product.setDescription("description");

        ProductDTO dto = converter.convert(product);

        assertEquals(product.getId(), dto.getId());
        assertEquals(product.getName(), dto.getName());
        assertEquals(product.getCategory(), dto.getCategory());
        assertEquals(product.getPrice(), dto.getPrice());
        assertEquals(product.getDiscount(), dto.getDiscount());
        assertEquals(BigDecimal.valueOf(0.5).setScale(2), dto.getFinalPrice());
        assertEquals(product.getDescription(), dto.getDescription());
    }

    @Test
    public void convertProductToDtoNullPriceNullPrice() {
        Product product = new Product();
        product.setId(1L);
        product.setName("name");
        product.setCategory("category");
        product.setPrice(null);
        product.setDiscount(BigDecimal.valueOf(0.5));
        product.setDescription("description");

        ProductDTO dto = converter.convert(product);

        assertEquals(product.getId(), dto.getId());
        assertEquals(product.getName(), dto.getName());
        assertEquals(product.getCategory(), dto.getCategory());
        assertEquals(product.getPrice(), dto.getPrice());
        assertEquals(product.getDiscount(), dto.getDiscount());
        assertEquals(null, dto.getFinalPrice());
        assertEquals(product.getDescription(), dto.getDescription());
    }

    @Test
    public void convertDtoToProduct() {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setName("name");
        dto.setCategory("category");
        dto.setPrice(BigDecimal.valueOf(1.0));
        dto.setDiscount(BigDecimal.valueOf(0.5));
        dto.setFinalPrice(BigDecimal.ONE);
        dto.setDescription("description");

        Product product = converter.convert(dto);

        assertEquals(dto.getId(), product.getId());
        assertEquals(dto.getName(), product.getName());
        assertEquals(dto.getCategory(), product.getCategory());
        assertEquals(dto.getPrice(), product.getPrice());
        assertEquals(dto.getDiscount(), product.getDiscount());
        assertEquals(dto.getDescription(), product.getDescription());
    }

    @Test
    public void convertDtoToProductNullDiscount() {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setName("name");
        dto.setCategory("category");
        dto.setPrice(BigDecimal.valueOf(1.0));
        dto.setDiscount(null);
        dto.setFinalPrice(BigDecimal.ONE);
        dto.setDescription("description");

        Product product = converter.convert(dto);

        assertEquals(dto.getId(), product.getId());
        assertEquals(dto.getName(), product.getName());
        assertEquals(dto.getCategory(), product.getCategory());
        assertEquals(dto.getPrice(), product.getPrice());
        assertEquals(BigDecimal.valueOf(0), product.getDiscount());
        assertEquals(dto.getDescription(), product.getDescription());
    }
}