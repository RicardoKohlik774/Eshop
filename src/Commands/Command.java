package Commands;

import Console.Console;
/**
 * Represents a command that can be executed in the application.
 */
public interface Command {
    /**
     * Executes the command in the console.
     */

    String execute(Console console);

    /**
     * Enables to exit the program via console input.
     */
    boolean exit();


}
