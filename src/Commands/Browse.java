package Commands;

import java.util.Scanner;

import Console.Konzole;
import Store.*;

public class Browse implements Command  {
    Scanner sc = new Scanner(System.in);

    @Override
    public String execute(Konzole konzole) {
        System.out.println("What would you like to browse? (food,clothing,appliances)");
        String choice = sc.next();

        if (choice.equalsIgnoreCase("food")) {
            for (Food f : Stock.getJidlo()) {
                System.out.println(f.getName() + ", stock: " + f.getStock() + ", price: $" + f.getPrice());
            }
        } else if (choice.equalsIgnoreCase("clothing")) {
            for (Obleceni o : Stock.getObleceni()) {
                System.out.println(o.getName() + ", stock: " + o.getStock() + ", price: $" + o.getPrice());
            }
        } else if (choice.equalsIgnoreCase("appliances")) {
            for (Elektro e : Stock.getElektro()) {
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
