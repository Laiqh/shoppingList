package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NameTooLong extends AbstractValidationRule<Product> {
    private int maxLength;

    public NameTooLong(@Value("${nameMaxLength}") int maxLength) {
        this.maxLength = maxLength;
    }

    public void validate(Product product) throws ValidationException {
        if (product.getName().length() > maxLength) {
            throw new ValidationException("Name can not be longer than " + maxLength);
        }
    }
}
