package Commands;

import Console.Konzole;
import Store.Predmet;

import java.util.ArrayList;

public class Stash implements Command {

    @Override
    public String execute(Konzole konzole) {
        ArrayList<ArrayList<Predmet>> ownedOrders = konzole.getLoggedUser().getOwned();

        if (ownedOrders.isEmpty()) {
            return "You do not own any products yet.";
        }

        System.out.println("=====$$$ YOUR CURRENT STASH o: $$$=====");

        int orderNumber = 1;
        for (ArrayList<Predmet> order : ownedOrders) {
            System.out.println("Order #" + orderNumber + ":");
            for (Predmet item : order) {
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
