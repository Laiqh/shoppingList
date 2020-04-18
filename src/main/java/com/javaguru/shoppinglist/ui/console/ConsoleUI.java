package com.javaguru.shoppinglist.ui.console;

import com.javaguru.shoppinglist.ui.console.action.Action;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Component
public class ConsoleUI {
    private List<Action> actions;

    public ConsoleUI(List<Action> actions) {
        this.actions = actions;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Integer userInput;

        while (true) {
            try {
                printMainMenu();
                userInput = Integer.valueOf(scanner.nextLine());
                actions.get(userInput).execute();
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
        for (Action action : actions) {
            System.out.println(actions.indexOf(action) + ". " + action);
        }
    }
}
