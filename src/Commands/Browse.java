package Commands;

import java.util.Scanner;

import Console.Console;
import Store.*;

/**
 * Lets the user view available products in different categories.
 * Shows food, clothing, or appliance items from the stock.
 */
public class Browse implements Command {
    Scanner sc = new Scanner(System.in);

    /**
     * Asks the user for a category and lists all items in that group with the stats like name, stock, and price.
     */
    @Override
    public String execute(Console console) {
        System.out.println("What would you like to browse? (food,clothing,appliances)");
        String choice = sc.next();

        if (choice.equalsIgnoreCase("food")) {
            for (Food f : Stock.getFood()) {
                System.out.println(f.getName() + ", stock: " + f.getStock() + ", price: $" + f.getPrice());
            }
        } else if (choice.equalsIgnoreCase("clothing")) {
            for (Clothes o : Stock.getObleceni()) {
                System.out.println(o.getName() + ", stock: " + o.getStock() + ", price: $" + o.getPrice());
            }
        } else if (choice.equalsIgnoreCase("appliances")) {
            for (Electro e : Stock.getElektro()) {
                System.out.println(e.getName() + ", stock: " + e.getStock() + ", price: $" + e.getPrice());
            }
        } else {
            return "Invalid category.";
        }

        return "These are the available products.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
