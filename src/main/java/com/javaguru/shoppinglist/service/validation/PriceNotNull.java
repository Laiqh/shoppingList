package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class PriceNotNull extends AbstractValidationRule<Product> {
    public void validate(Product product) throws ValidationException {
        if (product.getPrice() == null) {
            throw new ValidationException("Price can't be null");
        }
    }
}
