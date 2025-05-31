package Commands;

import Console.Console;
import Store.Product;

import java.util.ArrayList;

/**
 * Displays the list of products the user currently owns.
 */
public class Stash implements Command {

    /**
     * Shows the stash of owned items for the logged-in user.
     * If the user doesn't have any, it tells them they have no owned products.
     */
    @Override
    public String execute(Console console) {
        ArrayList<ArrayList<Product>> ownedOrders = console.getLoggedUser().getOwned();

        if (ownedOrders.isEmpty()) {
            return "You do not own any products yet.";
        }

        System.out.println("=====$$$ YOUR CURRENT STASH o: $$$=====");

        int orderNumber = 1;
        for (ArrayList<Product> order : ownedOrders) {
            System.out.println("Order #" + orderNumber + ":");
            for (Product item : order) {
                System.out.println("- " + item.getName() + " ($" + item.getPrice() + ")");
            }
            orderNumber++;
            System.out.println();
        }

        return "These are your owned products.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
