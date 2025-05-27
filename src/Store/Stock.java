package Store;

import java.io.Serializable;
import java.util.ArrayList;

public class Stock implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final ArrayList<Food> jidlo = new ArrayList<>();
    private static final ArrayList<Obleceni> obleceni = new ArrayList<>();
    private static final ArrayList<Elektro> elektro = new ArrayList<>();

    public static void stockStartup() {
        ArrayList<Predmet> loaded = Console.Data.loadStock();
        if (loaded.isEmpty()) {
            loadDefaultStock();
        } else {
            for (Predmet p : loaded) {
                if (p instanceof Food) {
                    jidlo.add((Food) p);
                } else if (p instanceof Obleceni) {
                    obleceni.add((Obleceni) p);
                } else if (p instanceof Elektro) {
                    elektro.add((Elektro) p);
                }
            }
        }
    }

    private static void loadDefaultStock() {
        elektro.add(new Elektro(25, "Sluchatka", true, Typ.APPLIANCE, "5 W/h"));
        elektro.add(new Elektro(55, "Pracka", true, Typ.APPLIANCE, "20 W/h"));

        obleceni.add(new Obleceni(22, "Dziny", true, Typ.CLOTHING, "Denim"));
        obleceni.add(new Obleceni(20, "Tricko", true, Typ.CLOTHING, "cotton"));

        jidlo.add(new Food(12, "Milk", true, Typ.FOOD, "15.9.2025"));
        jidlo.add(new Food(20, "Meat", true, Typ.FOOD, "15.9.2025"));

        Console.Data.saveStock();
    }

    public static Predmet Search(String name) {
        for (Food f : getJidlo()) {
            if (f.getName().equalsIgnoreCase(name)) return f;
        }
        for (Obleceni o : getObleceni()) {
            if (o.getName().equalsIgnoreCase(name)) return o;
        }
        for (Elektro e : getElektro()) {
            if (e.getName().equalsIgnoreCase(name)) return e;
        }
        return null;
    }

    public static ArrayList<Food> getJidlo() {
        return jidlo;
    }

    public static ArrayList<Obleceni> getObleceni() {
        return obleceni;
    }

    public static ArrayList<Elektro> getElektro() {
        return elektro;
    }
}
