package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NameTooShort extends AbstractValidationRule<Product> {
    private int minLength;

    public NameTooShort(@Value("${nameMinLength}") int minLength) {
        this.minLength = minLength;
    }

    public void validate(Product product) throws ValidationException {
        if (product.getName().length() < minLength) {
            throw new ValidationException("Name can not be shorter than " + minLength);
        }
    }
}
