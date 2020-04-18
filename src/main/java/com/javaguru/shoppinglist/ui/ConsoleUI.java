package com.javaguru.shoppinglist.ui;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Component
public class ConsoleUI {
    private static final int CREATE_PRODUCT = 1;
    private static final int FIND_BY_ID = 2;
    private static final int FIND_ALL = 3;
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
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
    }

    private void printMainMenu() {
        System.out.println("Choose an action:");
        System.out.println(CREATE_PRODUCT + ". Create product");
        System.out.println(FIND_BY_ID + ". Find product by id");
        System.out.println(FIND_ALL + ". Find all products");
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
            case FIND_ALL:
                findAllProducts();
                break;
            case EXIT:
                toContinue = false;
                break;
            default:
                System.out.println("Unknown command");
        }
    }

    private void createProduct() {
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
            id = service.add(product);
        } catch (ValidationException e) {
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

    private void findProductById() {
        System.out.println("Enter product id: ");
        Long id = Long.parseLong(scanner.nextLine());
        Product findProductResult = service.findById(id);
        System.out.println(findProductResult);
    }

    private void findAllProducts() {
        List<Product> products = service.findAll();
        System.out.println(products);
    }
}
