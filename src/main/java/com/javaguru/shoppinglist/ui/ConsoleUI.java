package com.javaguru.shoppinglist.ui;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.ValidationException;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleUI {
    private ProductService service;
    private Scanner scanner = new Scanner(System.in);

    public ConsoleUI(ProductService service) {
        this.service = service;
    }

    public void execute() {
        while (true) {
            try {
                System.out.println("1. Create product");
                System.out.println("2. Find product by id");
                System.out.println("3. Exit");
                Integer userInput = Integer.valueOf(scanner.nextLine());
                switch (userInput) {
                    case 1:
                        createProduct();
                        break;
                    case 2:
                        findProductById();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Unknown command");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format");
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
    }

    private void createProduct() {
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        System.out.println("Enter product category: ");
        String category = scanner.nextLine();
        System.out.println("Enter product price: ");
        BigDecimal price = new BigDecimal(scanner.nextLine());
        System.out.println("Enter product discount: ");
        BigDecimal discount = new BigDecimal(scanner.nextLine());
        System.out.println("Enter product description: ");
        String description = scanner.nextLine();
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        product.setDiscount(discount);
        product.setDescription(description);

        Long id;
        try {
            id = service.add(product);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Product with ID " + id + " has been added");
    }

    private void findProductById() {
        System.out.println("Enter product id: ");
        Long id = Long.parseLong(scanner.nextLine());
        Product findProductResult = service.findById(id);
        System.out.println(findProductResult);
    }
}
