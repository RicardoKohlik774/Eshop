package Console;

import Commands.*;
import Store.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Console {
    Scanner sc = new Scanner(System.in);
    private final Map<String, Command> commands = new HashMap<>();
    private boolean isLoggedIn = false;
    private User loggedUser;
    private boolean isAdmin;


    public Console() {
        Stock.stockStartup();
        start();
    }

    public void loginCommandPut() {
        commands.put("login", new Login());
    }

    public void userCommandsPut() {
        commands.put("browse", new Browse());
        commands.put("buy", new Buy());
        commands.put("remove", new Remove());
        commands.put("checkout", new Checkout());
        commands.put("stash", new Stash());
        commands.put("inbox", new Inbox());
    }

    public void adminCommandsPut() {
        commands.put("refill", new Refill());
        commands.put("browse", new Browse());
        commands.put("update", new Update());
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


    public void start() {
        System.out.println("Welcome to the Eshop! Please log in using your id.");
        System.out.println("(login, help)");
        loginCommandPut();
        while (!isLoggedIn) {
            System.out.print("--> ");
            String input = sc.nextLine();
            executeCommand(input);
        }

        if (isAdmin) {
            adminCommandsPut();
            adminInterface();
        } else {
            userCommandsPut();
            userInterface();
        }
    }

    public void userInterface() {
        while (true) {
            System.out.println("(browse, buy, remove, checkout, stash, inbox)");
            System.out.print("--> ");
            String input = sc.nextLine();
            if ("exit".equalsIgnoreCase(input)) break;
            executeCommand(input);
        }
    }

    public void adminInterface() {
        while (true) {
            System.out.println("(refill,browse,update)");
            System.out.print("--> ");
            String input = sc.nextLine();
            if ("exit".equalsIgnoreCase(input)) break;
            executeCommand(input);
        }
    }

    public void removeCommand(String name) {
        commands.remove(name);
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



    public void setAdmin(boolean admin) {
      isAdmin = admin;
    }


}
