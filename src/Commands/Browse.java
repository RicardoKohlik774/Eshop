package Commands;

import java.util.Scanner;

import Store.*;

public class Browse implements Command  {
Scanner sc = new Scanner(System.in);






    @Override
    public String execute() {
        System.out.println("What would you like to browse?");
        String choice = sc.next();
        if (choice.equalsIgnoreCase("food")) {
            for (Food f : Stock.getJidlo()) {
                System.out.println(f.getName());
            }
        } else if (choice.equalsIgnoreCase("clothing")) {
            for (Obleceni o : Stock.getObleceni()) {
                System.out.println(o.getName());
            }
        } else if (choice.equalsIgnoreCase("appliences")) {
            for (Elektro e : Stock.getElektro()) {
                System.out.println(e.getName());
            }
        } else {
            return "Unknown category.";
        }

        return "These are the available products.";
    }


    @Override
    public boolean exit() {
        return false;
    }
}
