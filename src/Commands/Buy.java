package Commands;

import Console.Console;
import Store.*;
import Console.Data;

import java.util.Scanner;

/**
 * Handles buying a product by inputting its name.
 * It checks if its in stock, asks for confirmation, and adds the item to the user's cart.
 */
public class Buy implements Command {

    /**
     * Lets the user type the product name, confirms the choice,
     * and updates the cart and stock if accepted.
     */
    @Override
    public String execute(Console console) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What product do you want to buy? ");

        String name = scanner.nextLine().trim();
        Product item = Stock.Search(name);

        if (item == null) {
            return "This product is not available.";
        }

        if (item.getStock() <= 0) {
            return "This product is out of stock.";
        }

        while (true) {
            System.out.print("Do you want to add '" + item.getName() + "' to your cart? (y/n): ");
            String answer = scanner.nextLine().trim();

            if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
                console.getLoggedUser().addToCart(item);
                item.setStock(item.getStock() - 1);
                Data.saveStock();
                Data.save(console.getLoggedUser());
                System.out.println("Your cart now contains:");
                for (Product p : console.getLoggedUser().getCart()) {
                    System.out.println("- " + p.getName());
                }

                return "Product was added to your cart. Ammount of " + item.getName() + " left: "  + item.getStock();
            } else if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no")) {
                return "Product was not added.";
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
