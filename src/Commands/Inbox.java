package Commands;

import Console.Console;
import Console.User;
import Store.Order;

import java.util.ArrayList;

public class Inbox implements Command {
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
