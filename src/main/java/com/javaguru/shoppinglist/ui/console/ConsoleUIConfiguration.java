package com.javaguru.shoppinglist.ui.console;

import com.javaguru.shoppinglist.ui.console.action.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConsoleUIConfiguration {
    private Action createProduct;
    private Action findProductById;
    private Action findAllProducts;
    private Action exit;

    @Autowired
    public ConsoleUIConfiguration(Action createProduct,
                                  Action findProductById,
                                  Action findAllProducts,
                                  Action exit) {
        this.createProduct = createProduct;
        this.findProductById = findProductById;
        this.findAllProducts = findAllProducts;
        this.exit = exit;
    }

    @Bean
    public ConsoleUI consoleUI() {
        List<Action> actions = new ArrayList<>();
        actions.add(exit);
        actions.add(createProduct);
        actions.add(findProductById);
        actions.add(findAllProducts);

        return new ConsoleUI(actions);
    }
}
