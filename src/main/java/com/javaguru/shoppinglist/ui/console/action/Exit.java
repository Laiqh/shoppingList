package com.javaguru.shoppinglist.ui.console.action;

import org.springframework.stereotype.Component;

@Component
public class Exit implements Action {
    private static final String NAME = "Exit";

    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public String toString() {
        return NAME;
    }
}
