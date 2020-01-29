package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ShoppingListApplication {

    public static void main(String[] args) {
        Map<Long, Product> productRepository = new HashMap<>();
        Long productIdSequence = 0L;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("1. Create product");
                System.out.println("2. Find product by id");
                System.out.println("3. Exit");
                Integer userInput = Integer.valueOf(scanner.nextLine());
                switch (userInput) {
                    case 1:
                        System.out.println("Enter product name: ");
                        String name = scanner.nextLine();
                        if (!nameIsValid(name)) {
                            System.out.println("Name is invalid");
                            break;
                        }
                        System.out.println("Enter product category: ");
                        String category = scanner.nextLine();
                        System.out.println("Enter product price: ");
                        BigDecimal price = new BigDecimal(scanner.nextLine());
                        if (!priceIsValid(price)) {
                            System.out.println("Price is invalid");
                            break;
                        }
                        System.out.println("Enter product discount: ");
                        BigDecimal discount = new BigDecimal(scanner.nextLine());
                        if (!discountIsValid(discount)) {
                            System.out.println("Discount is invalid");
                            break;
                        }
                        System.out.println("Enter product description: ");
                        String description = scanner.nextLine();
                        Product product = new Product();
                        product.setName(name);
                        product.setCategory(category);
                        product.setPrice(price);
                        product.setDiscount(discount);
                        product.setDescription(description);
                        product.setId(productIdSequence);
                        productRepository.put(productIdSequence, product);
                        productIdSequence++;
                        System.out.println("Result: " + product.getId());
                        break;
                    case 2:
                        System.out.println("Enter product id: ");
                        long id = scanner.nextLong();
                        Product findProductResult = productRepository.get(id);
                        System.out.println(findProductResult);
                        break;
                    case 3:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
            }
        }
    }

    private static boolean priceIsValid(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) > 0;
    }

    private static boolean discountIsValid(BigDecimal discount) {
        return discount.compareTo(BigDecimal.ONE) < 0;
    }

    private static boolean nameIsValid(String name) {
        return name.length() >= 3 && name.length() <= 32;
    }
}
