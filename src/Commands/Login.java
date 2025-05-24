package Commands;

import Console.Konzole;
import Console.User;

import java.util.Scanner;

public class Login implements Command {
    private String id;

    @Override
    public String execute(Konzole konzole) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter your id (8-digits): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                return "Login cancelled";
            }

            if (input.matches("\\d{8}")) {
                konzole.setLoggedIn(true);
                User user = new User(input);
                konzole.setLoggedUser(user);
                id = input;
                return "Access granted, welcome.";
            } else {
                System.out.println("Invalid ID.");
            }
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
