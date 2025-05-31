package Commands;

import Console.Data;
import Console.Console;
import Store.Product;

import java.util.Scanner;

/**
 * Removes a selected product from the logged user's cart.
 */
public class Remove implements Command {

    /**
     * Asks the user which product to remove, confirms the choice, and updates the cart.
     */
    @Override
    public String execute(Console console) {
        Scanner scanner = new Scanner(System.in);

        if (console.getLoggedUser().getCart().isEmpty()) {
            return "Your cart is empty.";
        }

        System.out.println("Which product would you like to remove from your cart?");
        String name = scanner.nextLine().trim();

        Product remove = null;

        for (Product p : console.getLoggedUser().getCart()) {
            if (p.getName().equalsIgnoreCase(name)) {
                remove = p;
                break;
            }
        }

        if (remove == null) {
            return "It seems this product is not in your cart, try again.";
        }

        while (true) {
            System.out.print("Are you sure you want to remove '" + remove.getName() + "' from your cart? (y/n): ");
            String confirm = scanner.nextLine().trim();

            if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
                console.getLoggedUser().getCart().remove(remove);
                Data.save(console.getLoggedUser());
                System.out.println("Product was removed from your cart.");
                break;
            } else if (confirm.equalsIgnoreCase("n") || confirm.equalsIgnoreCase("no")) {
                System.out.println("Product was not removed.");
                break;
            } else {
                System.out.println("Invalid input.");
                System.out.println();
            }
        }

        System.out.println("Current cart contains:");
        for (Product p : console.getLoggedUser().getCart()) {
            System.out.println("- " + p.getName());
        }

        return "";
    }


    @Override
    public boolean exit() {
        return false;
    }
}
