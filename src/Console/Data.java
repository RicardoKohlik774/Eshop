package Console;

import Store.Predmet;
import Store.Stock;

import java.io.*;
import java.util.ArrayList;

public class Data {
                        //MAKE A USERS FILE AND STORE THE USERS THERE?
                        //MAKE SURE TO SAVE AND LOAD WHERE NECESSARY
                        //ADD A RESET COMMAND FOR THE ID
    private static final String FILE_NAME = "userdata.serialization";
    private static final String STOCK_FILE = "stock.serialization";

    public static void save(User user) {
        String fileName = "users/" + user.getId() + ".ser";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(user);
            System.out.println("User data has been saved.");
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }

    public static User load(String id) {
        String fileName = "users/" + id + ".ser";
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (User) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved user data or error loading: " + e.getMessage());
            return null;
        }
    }


    public static void saveStock() {
        ArrayList<Predmet> all = new ArrayList<>();
        all.addAll(Stock.getJidlo());
        all.addAll(Stock.getElektro());
        all.addAll(Stock.getObleceni());

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(STOCK_FILE))) {
            out.writeObject(all);
        } catch (IOException e) {
            System.out.println("Error saving stock: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked") //MAKES THE WARNING NOT POP UP
    public static ArrayList<Predmet> loadStock() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(STOCK_FILE))) {
            return (ArrayList<Predmet>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved stock or error loading: " + e.getMessage());
            return new ArrayList<>();
        }
    }


}
