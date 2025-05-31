package Commands;

import Console.Data;
import Console.Console;
import Store.*;
import java.util.Scanner;


/**
 * Lets the admin add more units of a product to the stock.
 */
public class Refill implements Command {

    /**
     * Asks the admin to choose a product and a quantity, then updates stock if confirmed.
     */
    @Override
    public String execute(Console console) {
        Scanner sc = new Scanner(System.in);

        System.out.println("What product would you like to refill?");
        String productName = sc.nextLine().trim();

        Product found = Stock.Search(productName);

        if (found == null) {
            return "Product was not found.";
        }

        int amountToAdd;
        while (true) {
            System.out.print("How many units do you want to add?: ");
            int input = sc.nextInt();
            try {
                amountToAdd = input;
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
                Data.saveStock();
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
