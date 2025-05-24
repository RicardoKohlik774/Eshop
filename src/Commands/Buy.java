package Commands;

import Console.Konzole;
import Store.*;

import java.util.Scanner;

public class Buy implements Command {

    @Override
    public String execute(Konzole konzole) {       // ADD HOW MANY
        Scanner scanner = new Scanner(System.in);
        System.out.print("What product do you want to buy? ");
        String name = scanner.nextLine().trim();
        Predmet item = null;

        for (Food f : Stock.getJidlo()) {
            if (f.getName().equalsIgnoreCase(name)) {
                item = f;
                break;
            }
        }

        for (Obleceni o : Stock.getObleceni()) {
            if (o.getName().equalsIgnoreCase(name)) {
                item = o;
                break;
            }
        }

        for (Elektro e : Stock.getElektro()) {
            if (e.getName().equalsIgnoreCase(name)) {
                item = e;
                break;
            }
        }
        if (item == null) {
            return "This product is not available.";
        }

        System.out.print("Do you want to add '" + item.getName() + "' to your cart? (y/n): ");
        String answer = scanner.nextLine().trim();

        if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
            konzole.getLoggedUser().addToCart(item);

            System.out.println("Your cart now contains:");
            for (Predmet p : konzole.getLoggedUser().getCart()) {
                System.out.println("- " + p.getName());
            }

            return "Product was added to your cart.";
        } else {
            return "Product was not added.";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
