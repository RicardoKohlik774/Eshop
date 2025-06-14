package Console;

import Store.Product;
import Store.Stock;

import java.io.*;
import java.util.ArrayList;

/**
 * A class for saving and loading user and stock data using serialization.
 */
public class Data {

    private static final String STOCK_FILE = "stock.serialization";

    /**
     * Saves a user's data to a file named after the user's id.
     */
    public static void save(User user) {
        String fileName = "users/" + user.getId() + ".ser";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(user);
            System.out.println("User data has been saved.");
        } catch (IOException e) {
            System.out.println("No user with this id found.");
        }
    }

    /**
     * Loads user data from file based on the user id.
     * Returns null if the file does not exist or cannot be read.
     */
    public static User load(String id) {
        String fileName = "users/" + id + ".ser";
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (User) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading" + e.getMessage());
            return null;
        }
    }

    /**
     * Saves all stock items (food, electronics, clothing) to a single file.
     */
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

    /**
     * Loads the stock data from the file (serialization).
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Product> loadStock() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(STOCK_FILE))) {
            return (ArrayList<Product>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
