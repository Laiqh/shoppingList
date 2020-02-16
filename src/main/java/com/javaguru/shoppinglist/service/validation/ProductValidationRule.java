package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class ProductValidationRule extends AbstractValidationRuleList<Product> implements ValidationRule<Product> {
}
