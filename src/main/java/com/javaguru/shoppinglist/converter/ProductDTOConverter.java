package com.javaguru.shoppinglist.converter;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ProductDTOConverter {

    public ProductDTO convert(Product product) {
        ProductDTO dto = new ProductDTO();

        dto.setId(product.getId());

        dto.setName(product.getName());

        dto.setCategory(product.getCategory());

        dto.setPrice(product.getPrice());

        dto.setDiscount(product.getDiscount());

        if (product.getPrice() != null && product.getDiscount() != null) {
            BigDecimal finalPrice = product.getPrice().multiply(BigDecimal.ONE.subtract(product.getDiscount())).setScale(2, RoundingMode.HALF_EVEN);
            dto.setFinalPrice(finalPrice);
        }

        dto.setDescription(product.getDescription());

        return dto;
    }

    public Product convert(ProductDTO dto) {
        Product product = new Product();

        product.setId(dto.getId());

        product.setName(dto.getName());

        product.setCategory(dto.getCategory());

        product.setPrice(dto.getPrice());

        if (dto.getDiscount() == null) {
            product.setDiscount(BigDecimal.ZERO);
        } else {
            product.setDiscount(dto.getDiscount());
        }

        product.setDescription(dto.getDescription());

        return product;
    }
}
