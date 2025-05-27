package Commands;

import Console.Data;
import Console.Konzole;
import Console.User;

import java.util.Scanner;

public class Login implements Command {
    private String id;

    @Override
    public String execute(Konzole konzole) {
        Scanner scanner = new Scanner(System.in);
        String adminId = "00000000";

        while (true) {
            System.out.print("Enter your id (8-digits): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                return "Login cancelled";
            }

            if (input.matches("\\d{8}")) {
                if (input.equals(adminId)) {
                    konzole.setLoggedIn(true);
                    konzole.setAdmin(true);
                    konzole.setLoggedUser(null);
                    konzole.removeCommand("login");
                    return "Admin access granted.";
                } else {
                    User user = Data.load(input);
                    if (user == null) {
                        System.out.println("New user detected, Creating a new user...(REMEMBER YOUR ID!): ");
                        user = new User(input);
                    }

                    Data.save(user);
                    konzole.setLoggedIn(true);
                    konzole.setAdmin(false);
                    konzole.setLoggedUser(user);
                    id = input;
                    konzole.removeCommand("login");
                    return "Access granted, welcome.";
                }
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
