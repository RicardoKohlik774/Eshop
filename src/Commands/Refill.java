package Commands;

import Console.Konzole;
import Store.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Refill implements Command {

    @Override
    public String execute(Konzole konzole) {
        Scanner sc = new Scanner(System.in);

        System.out.println("What product would you like to refill?");
        String productName = sc.nextLine().trim();

        Predmet found = Stock.Search(productName);

        if (found == null) {
            return "Product was not found.";
        }

        int amountToAdd = 0;
        while (true) {
            System.out.print("How many units do you want to add?: ");
            String input = sc.nextLine().trim();
            try {
                amountToAdd = Integer.parseInt(input);
                if (amountToAdd <= 0) {
                    System.out.println("You cant add 0 products...");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
            System.out.println();
        }

        while (true) {
            System.out.print("Are you sure you want to add " + amountToAdd + " units of " + found.getName() + " to stock? (y/n): ");
            String confirmation = sc.nextLine().trim();
            if (confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes")) {
                found.setStock(found.getStock() + amountToAdd);
                return "Refill completed. Current stock of " + found.getName() + " is: " + found.getStock();
            } else if (confirmation.equalsIgnoreCase("n") || confirmation.equalsIgnoreCase("no")) {
                return "Refill was cancelled.";
            } else {
                System.out.println("Invalid input.");
                System.out.println();
            }
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
