package Commands;

import Console.Console;
import Console.User;
import Store.Order;

import java.util.ArrayList;

/**
 * Shows the list of orders placed by the logged-in user.
 */
public class Inbox implements Command {

    /**
     * Prints all orders of the logged-in user.
     * If there are no orders, tells the user he doesn't have any orders yet.
     */
    @Override
    public String execute(Console console) {
        User user = console.getLoggedUser();
        ArrayList<Order> orders = user.getOrders();

        if (orders.isEmpty()) {
            return "You have no orders yet.";
        }

        System.out.println("Your Orders:");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println("Order #" + (i + 1) + ":");
            orders.get(i).print();
            System.out.println();
        }

        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
