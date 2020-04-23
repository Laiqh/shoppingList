package com.javaguru.shoppinglist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@Import(ApplicationConfiguration.class)
public class ShoppingListApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShoppingListApplication.class);
    }
}
