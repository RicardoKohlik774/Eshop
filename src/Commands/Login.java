package Commands;

import java.util.Scanner;

public class Login implements Command {
    @Override
    public String execute() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Zadej sve 8mistne ID: ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Prihlaseni bylo zruseno.");
                break;
            }

            if (input.matches("\\d{8}")) {
                System.out.println("ID nacteno, vitejte.");
                break;
            } else {
                System.out.println("Neplatne ID.");
            }
        }
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
