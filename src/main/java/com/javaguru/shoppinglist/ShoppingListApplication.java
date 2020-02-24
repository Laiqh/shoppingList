package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.InMemoryRepository;
import com.javaguru.shoppinglist.repository.Repository;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.*;
import com.javaguru.shoppinglist.ui.ConsoleUI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

class ShoppingListApplication {
    private Repository<Product> repository;
    private ProductService service;
    private ProductValidationRule rules;
    private ConsoleUI ui;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        ConsoleUI console = context.getBean(ConsoleUI.class);
        console.execute();
    }

    public ShoppingListApplication() {
        repository = new InMemoryRepository<>();

        rules = new ProductValidationRule();
        rules.add(new NotNull());

        rules.add(new NameNotNull());
        rules.add(new NameTooShort(3));
        rules.add(new NameTooLong(32));
        rules.add(new NameUnique(repository));

        rules.add(new PriceNotNull());
        rules.add(new PriceGreaterThanZero());

        rules.add(new DiscountNotNull());
        rules.add(new DiscountLessThanOrEqualsToOne());
        rules.add(new DiscountGreaterThanOrEqualsToZero());
        rules.add(new DiscountMinPrice(new BigDecimal("20")));

        service = new ProductService(rules, repository);
        ui = new ConsoleUI(service);
    }

    private void execute() {
        this.ui.execute();
    }
}
