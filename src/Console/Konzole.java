package Console;

import Commands.Command;
import Commands.Login;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Konzole {
    Scanner sc = new Scanner(System.in);
    private Map<String, Command> commands = new HashMap<>();


    public void commandsPut(){
          commands.put("login",new Login());
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
    System.out.println(); //prikazy
    System.out.println("--> ");
    String input = sc.nextLine().trim();
    if ("exit".equalsIgnoreCase(input)) {
        break;
    }
    executeCommand(input);
}

}


}
