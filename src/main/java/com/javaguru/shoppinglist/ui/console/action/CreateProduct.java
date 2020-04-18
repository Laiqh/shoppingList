package com.javaguru.shoppinglist.ui.console.action;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class CreateProduct implements Action {
    private static final String NAME = "Create product";

    private final ProductService productService;

    private Scanner scanner = new Scanner(System.in);

    @Autowired
    public CreateProduct(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        String name = requestName();
        String category = requestCategory();
        BigDecimal price = requestPrice();
        BigDecimal discount = requestDiscount();
        String description = requestDescription();

        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        product.setDiscount(discount);
        product.setDescription(description);

        Long id;
        try {
            id = productService.add(product);
        } catch (
                ValidationException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Product with ID " + id + " has been added");
    }

    private String requestName() {
        System.out.println("Enter name: ");
        return scanner.nextLine();
    }

    private String requestCategory() {
        System.out.println("Enter category: ");
        return scanner.nextLine();
    }

    private BigDecimal requestPrice() {
        System.out.println("Enter price: ");
        return new BigDecimal(scanner.nextLine());
    }

    private BigDecimal requestDiscount() {
        System.out.println("Enter discount: ");
        return new BigDecimal(scanner.nextLine());
    }

    private String requestDescription() {
        System.out.println("Enter product description: ");
        return scanner.nextLine();
    }

    @Override
    public String toString() {
        return NAME;
    }
}
