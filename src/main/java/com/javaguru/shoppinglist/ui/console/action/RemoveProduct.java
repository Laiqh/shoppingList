package com.javaguru.shoppinglist.ui.console.action;

import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RemoveProduct implements Action {
    private static final String NAME = "Remove product by ID";

    private final ProductService productService;

    private Scanner scanner = new Scanner(System.in);

    @Autowired
    public RemoveProduct(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        System.out.println("Enter product id: ");
        Long id = Long.parseLong(scanner.nextLine());
        productService.remove(id);
    }

    @Override
    public String toString() {
        return NAME;
    }
}
