package Console;

import java.util.Scanner;

public class Konzole {
    Scanner sc = new Scanner(System.in);



    public void commandsPut(){

    }

public void login(User user){
    System.out.println("Please enter your id (8-digit code):");
    String input = sc.next();
    if (input.matches("[0-9]{8}")){
            start();
    }
    else {
        System.out.println("Wrong id, try again.");
    }
}

public void start(){
while (true) {
    System.out.println("Welcome to the Eshop! What would you like to do today?");
    System.out.println("--> ");
}
}


}
