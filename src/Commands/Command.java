package Commands;

import Console.Console;

public interface Command {

    String execute(Console console);
    boolean exit();


}
