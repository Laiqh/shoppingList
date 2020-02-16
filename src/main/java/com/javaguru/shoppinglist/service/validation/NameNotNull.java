package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class NameNotNull extends AbstractValidationRule<Product> {
    public void validate(Product product) throws ValidationException {
        if (product.getName() == null) {
            throw new ValidationException("Name can not be null");
        }
    }
}
