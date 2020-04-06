package com.javaguru.shoppinglist.ui.console.action;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllProducts implements Action {
    private static final String NAME = "Find all products";

    private final ProductService productService;

    public FindAllProducts(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        List<Product> products = productService.findAll();
        System.out.println(products);
    }
}
