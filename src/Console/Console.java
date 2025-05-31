package Console;

import Commands.*;
import Store.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The class that controls the interface and command execution.
 */
public class Console {
    Scanner sc = new Scanner(System.in);
    private final Map<String, Command> commands = new HashMap<>();
    private boolean isLoggedIn = false;
    private User loggedUser;
    private boolean isAdmin;

    /**
     * Constructor that starts the stock system and begins the login process.
     */
    public Console() {
        Stock.stockStartup();
        start();
    }


    /**
     * Adds the login and help commands before logging in.
     */
    public void loginCommandPut() {
        commands.put("login", new Login());
        commands.put("help", new Help());
    }

    /**
     * Adds the commands available to a user.
     */
    public void userCommandsPut() {
        commands.put("browse", new Browse());
        commands.put("buy", new Buy());
        commands.put("remove", new Remove());
        commands.put("checkout", new Checkout());
        commands.put("stash", new Stash());
        commands.put("inbox", new Inbox());
        commands.put("help", new Help());
    }

    /**
     * Adds the commands available to an admin.
     */
    public void adminCommandsPut() {
        commands.put("refill", new Refill());
        commands.put("browse", new Browse());
        commands.put("update", new Update());
        commands.put("help", new Help());
    }

    /**
     * Executes a command depending on its name. If not logged in, only login and help commands are allowed.
     */
    public void executeCommand(String name) {
        if (!isLoggedIn && (!name.equalsIgnoreCase("login") && (!name.equalsIgnoreCase("help")))) {
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

    /**
     * Starts the console and handles the login process.
     * Depending on the id, switches to the user or admin interface.
     */
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

    /**
     * Handles the interface for a user, allowing them to enter user-only commands.
     */
    public void userInterface() {
        while (true) {
            System.out.println("(browse, buy, remove, checkout, stash, inbox,help)");
            System.out.print("--> ");
            String input = sc.nextLine();
            if ("exit".equalsIgnoreCase(input)) break;
            executeCommand(input);
        }
    }

    /**
     * Handles the interface for an admin, allowing them to enter admin-only commands.
     */
    public void adminInterface() {
        while (true) {
            System.out.println("(refill,browse,update,help)");
            System.out.print("--> ");
            String input = sc.nextLine();
            if ("exit".equalsIgnoreCase(input)) break;
            executeCommand(input);
        }
    }

    /**
     * Removes a command by name from the available commands.
     * Is used to remove the login command after the login is complete
     */
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


    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
      isAdmin = admin;
    }


}
