package Commands;

import Console.Console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Displays help instructions based on the type of user.
 * If no one is logged in, it shows how to log in.
 */
public class Help implements Command {

    /**
     * Reads and displays the correct text from a file depending on the user.
     */
    @Override
    public String execute(Console console) {
        String fileName = "";

        if (console.isAdmin()) {
            fileName = "src\\adminHelp.txt";
        } else if (console.getLoggedUser() == null){
            fileName = "src\\Help.txt";
        }
        else {
            fileName = "src\\userHelp.txt";
        }

        StringBuilder helpText = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                helpText.append(line).append(System.lineSeparator());
            }
            return helpText.toString().trim();
        } catch (IOException e) {
            return "Error loading help file.";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
