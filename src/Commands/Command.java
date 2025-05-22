package Commands;

import Console.Konzole;

public interface Command {

    String execute(Konzole konzole);
    boolean exit();


}
