package Store;

import java.io.*;
import java.util.ArrayList;
import Console.Data;

public class Stock implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final ArrayList<Food> food = new ArrayList<>();
    private static final ArrayList<Clothes> CLOTHES = new ArrayList<>();
    private static final ArrayList<Electro> ELECTRO = new ArrayList<>();

    public static void stockStartup() {
        ArrayList<Product> loaded = Data.loadStock();
        if (loaded.isEmpty()) {
            loadDefaultStock();
        } else {
            for (Product p : loaded) {
                if (p instanceof Food) {
                    food.add((Food) p);
                } else if (p instanceof Clothes) {
                    CLOTHES.add((Clothes) p);
                } else if (p instanceof Electro) {
                    ELECTRO.add((Electro) p);
                }
            }
        }
    }

    public static void loadStockFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 3) {
                    continue;
                }
                String type = parts[0].trim().toUpperCase();
                String name = parts[1].trim();
                int price;
                try {
                    price = Integer.parseInt(parts[2].trim());
                } catch (NumberFormatException e) {
                    continue;
                }

                if (type.equals("FOOD")) {
                    food.add(new Food(price, name, Type.FOOD));
                } else if (type.equals("CLOTHES")) {
                    CLOTHES.add(new Clothes(price, name, Type.CLOTHING));
                } else if (type.equals("ELECTRO")) {
                    ELECTRO.add(new Electro(price, name, Type.ELECTRO));
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read stock file: " + e.getMessage());
        }
    }



    private static void loadDefaultStock() {
        loadStockFromFile("stock.txt");
        Data.saveStock();
    }

    public static Product Search(String name) {
        for (Food f : getFood()) {
            if (f.getName().equalsIgnoreCase(name)) return f;
        }
        for (Clothes o : getObleceni()) {
            if (o.getName().equalsIgnoreCase(name)) return o;
        }
        for (Electro e : getElektro()) {
            if (e.getName().equalsIgnoreCase(name)) return e;
        }
        return null;
    }

    public static ArrayList<Food> getFood() {
        return food;
    }

    public static ArrayList<Clothes> getObleceni() {
        return CLOTHES;
    }

    public static ArrayList<Electro> getElektro() {
        return ELECTRO;
    }
}
