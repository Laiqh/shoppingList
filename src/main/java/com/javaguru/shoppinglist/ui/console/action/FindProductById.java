package com.javaguru.shoppinglist.ui.console.action;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindProductById implements Action {
    private static final String NAME = "Find product by ID";

    private final ProductService productService;

    private Scanner scanner = new Scanner(System.in);

    @Autowired
    public FindProductById(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        System.out.println("Enter product id: ");
        Long id = Long.parseLong(scanner.nextLine());
        Product findProductResult = productService.findById(id);
        System.out.println(findProductResult);
    }

    @Override
    public String toString() {
        return NAME;
    }
}
