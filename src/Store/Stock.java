package Store;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Stock implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final ArrayList<Food> jidlo = new ArrayList<>();
    private static final ArrayList<Clothes> CLOTHES = new ArrayList<>();
    private static final ArrayList<Electro> ELECTRO = new ArrayList<>();

    public static void stockStartup() {
        ArrayList<Product> loaded = Console.Data.loadStock();
        if (loaded.isEmpty()) {
            loadDefaultStock();
        } else {
            for (Product p : loaded) {
                if (p instanceof Food) {
                    jidlo.add((Food) p);
                } else if (p instanceof Clothes) {
                    CLOTHES.add((Clothes) p);
                } else if (p instanceof Electro) {
                    ELECTRO.add((Electro) p);
                }
            }
        }
    }

    private static void loadDefaultStock() {
        ELECTRO.add(new Electro(25, "Sluchatka", true, Type.ELECTRO));
        ELECTRO.add(new Electro(55, "Pracka", true, Type.ELECTRO));

        CLOTHES.add(new Clothes(22, "Dziny", true, Type.CLOTHING));
        CLOTHES.add(new Clothes(20, "Tricko", true, Type.CLOTHING));

        jidlo.add(new Food(12, "Milk", true, Type.FOOD));
        jidlo.add(new Food(20, "Meat", true, Type.FOOD));

        Console.Data.saveStock();
    }

    public static Product Search(String name) {
        for (Food f : getJidlo()) {
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

    public static ArrayList<Food> getJidlo() {
        return jidlo;
    }

    public static ArrayList<Clothes> getObleceni() {
        return CLOTHES;
    }

    public static ArrayList<Electro> getElektro() {
        return ELECTRO;
    }
}
