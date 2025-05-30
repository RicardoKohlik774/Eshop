package Commands;

import java.util.Scanner;

import Console.Console;
import Store.*;

public class Browse implements Command  {
    Scanner sc = new Scanner(System.in);

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
