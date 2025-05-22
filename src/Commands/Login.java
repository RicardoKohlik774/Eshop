package Commands;

import Console.Konzole;

import java.util.Scanner;

public class Login implements Command {
    private String id;

    @Override
    public String execute(Konzole konzole) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Zadej sve 8mistne ID: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                return "Prihlaseni bylo zruseno.";
            }

            if (input.matches("\\d{8}")) {
                konzole.setLoggedIn(true);
                id = input;
                return "ID nacteno, vitejte.";
            } else {
                System.out.println("Neplatne ID.");
            }
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
