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
        Command command = commands.get(name);
        if (command != null) {
            System.out.println(command.execute());
        } else {
            System.out.println("Neznamy prikaz: " + name);
        }
    }
public void start(){
while (true) {
    System.out.println("Welcome to the Eshop! What would you like to do today?");
    System.out.println("(log in first)");
    System.out.print("--> ");
    String input = sc.nextLine();
    if ("exit".equalsIgnoreCase(input)) {
        break;
    }
    executeCommand(input);
}

}


}
