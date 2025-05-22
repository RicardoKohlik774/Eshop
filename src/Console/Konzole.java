package Console;

import Commands.Browse;
import Commands.Command;
import Commands.Login;
import Store.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Konzole {
    Scanner sc = new Scanner(System.in);
    private Map<String, Command> commands = new HashMap<>();
    Stock stock = new Stock();
    private boolean isLoggedIn = false;


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
public void start(){
    System.out.println("Welcome to the Eshop! Please log in using your id.");
    System.out.println();
while (true) {
    System.out.println("(Browse)");
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
}
