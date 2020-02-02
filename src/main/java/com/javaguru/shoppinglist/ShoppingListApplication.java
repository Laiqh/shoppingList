package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.InMemoryRepository;
import com.javaguru.shoppinglist.repository.Repository;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.ui.ConsoleUI;

class ShoppingListApplication {
    private Repository<Product> repository;
    private ProductService service;
    private ConsoleUI ui;

    public static void main(String[] args) {
        ShoppingListApplication application = new ShoppingListApplication();
        application.execute();
    }

    public ShoppingListApplication() {
        this.repository = new InMemoryRepository<>();
        this.service = new ProductService(repository);
        this.ui = new ConsoleUI(service);
    }

    private void execute() {
        this.ui.execute();
    }
}
