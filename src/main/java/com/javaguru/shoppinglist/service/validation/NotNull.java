package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class NotNull extends AbstractValidationRule<Product> {
    public void validate(Product product) throws ValidationException {
        if (product == null) {
            throw new ValidationException("Product can not be null");
        }
    }
}
