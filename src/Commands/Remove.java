package Commands;

import Console.Konzole;
import Store.Predmet;

import java.util.Scanner;

public class Remove implements Command {

    @Override
    public String execute(Konzole konzole) {
        Scanner scanner = new Scanner(System.in);

        if (konzole.getLoggedUser().getCart().isEmpty()) {
            return "Your cart is empty.";
        }

        System.out.println("Which product would you like to remove from your cart?");
        String name = scanner.nextLine().trim();

        Predmet remove = null;

        for (Predmet p : konzole.getLoggedUser().getCart()) {
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
                konzole.getLoggedUser().getCart().remove(remove);
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
        for (Predmet p : konzole.getLoggedUser().getCart()) {
            System.out.println("- " + p.getName());
        }

        return "";
    }


    @Override
    public boolean exit() {
        return false;
    }
}
