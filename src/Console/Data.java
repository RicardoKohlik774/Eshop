package Console;

import Store.Product;
import Store.Stock;

import java.io.*;
import java.util.ArrayList;

public class Data {


    private static final String STOCK_FILE = "stock.serialization";

    public static void save(User user) {
        String fileName = "users/" + user.getId() + ".ser";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(user);
            System.out.println("User data has been saved.");
        } catch (IOException e) {
            System.out.println("No user with this id found.");
        }
    }

    public static User load(String id) {
        String fileName = "users/" + id + ".ser";
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (User) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading" + e.getMessage());
            return null;
        }
    }


    public static void saveStock() {
        ArrayList<Product> all = new ArrayList<>();
        all.addAll(Stock.getFood());
        all.addAll(Stock.getElektro());
        all.addAll(Stock.getObleceni());

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(STOCK_FILE))) {
            out.writeObject(all);
        } catch (IOException e) {
            System.out.println("Error saving stock: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked") //MAKES THE WARNING NOT POP UP
    public static ArrayList<Product> loadStock() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(STOCK_FILE))) {
            return (ArrayList<Product>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading: " + e.getMessage());
            return new ArrayList<>();
        }
    }


}
