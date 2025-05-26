package Console;

import Commands.*;
import Store.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Konzole {
    Scanner sc = new Scanner(System.in);
    private Map<String, Command> commands = new HashMap<>();
    Stock stock = new Stock();
    private boolean isLoggedIn = false;
    private User loggedUser;


    public Konzole() {
       stock.addObleceni();
       stock.addElektro();
       stock.addPotraviny();
        commandsPut();
        start();
    }

    public void commandsPut(){
          commands.put("login",new Login());
          commands.put("browse",new Browse());
          commands.put("buy", new Buy());
          commands.put("remove", new Remove());
          commands.put("checkout", new Checkout());
          commands.put("refill", new Refill());
          commands.put("stash", new Stash());
    }

    public void executeCommand(String name) {
        if (!isLoggedIn && !name.equalsIgnoreCase("login")) {
            System.out.println("Log in first by using the command 'login'.");
            return;
        }

        Command command = commands.get(name);
        if (command != null) {
            System.out.println(command.execute(this));
        } else {
            System.out.println("Neznamy prikaz: " + name);
        }
    }


public void start(){  //FIX THE PRINTS
    System.out.println("Welcome to the Eshop! Please log in using your id.");
    System.out.println("(login,help)");
while (true) {
    System.out.println("(browse,buy,remove,checkout,refill,stash)");
    System.out.print("--> ");
    String input = sc.nextLine();
    if ("exit".equalsIgnoreCase(input)) {
        break;
    }
    executeCommand(input);
}
}

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }


    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User user) {
        this.loggedUser = user;
    }

    public Stock getStock() {
        return stock;
    }
}
