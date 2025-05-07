package Commands;

import java.util.Scanner;
import Store.Food;

public class Browse implements Command  {
Scanner sc = new Scanner(System.in);
// Food food = new Food();

public void selection(){
    System.out.println("What would you like to browse?");
    String choice = sc.next();
    switch (choice){
        case ("food"):   //vypsat nazvy produktu

        case ("clothing"):

        case ("appliences"):
    }
}

    @Override
    public String execute() {
        return "";
    }


    @Override
    public boolean exit() {
        return false;
    }
}
