package com.javaguru.shoppinglist.ui;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class ConsoleUI {
    private static final int CREATE_PRODUCT = 1;
    private static final int FIND_BY_ID = 2;
    private static final int EXIT = 0;

    private ProductService service;
    private Scanner scanner = new Scanner(System.in);
    private boolean toContinue = true;

    @Autowired
    public ConsoleUI(ProductService service) {
        this.service = service;
    }

    public void execute() {
        while (toContinue) {
            try {
                printMainMenu();
                Integer userInput = Integer.valueOf(scanner.nextLine());
                processMainMenuInput(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format");
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
    }

    private void printMainMenu() {
        System.out.println("Choose an action:");
        System.out.println(CREATE_PRODUCT + ". Create product");
        System.out.println(FIND_BY_ID + ". Find product by id");
        System.out.println(EXIT + ". Exit");
    }

    private void processMainMenuInput(Integer userInput) {
        switch (userInput) {
            case CREATE_PRODUCT:
                createProduct();
                break;
            case FIND_BY_ID:
                findProductById();
                break;
            case EXIT:
                toContinue = false;
                break;
            default:
                System.out.println("Unknown command");
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
