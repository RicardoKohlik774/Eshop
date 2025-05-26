package Store;

import java.io.Serializable;
import java.util.ArrayList;

public class Stock implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final ArrayList<Food> jidlo = new ArrayList<>();
    private static final ArrayList<Obleceni> obleceni = new ArrayList<>();
    private static final ArrayList<Elektro> elektro = new ArrayList<>();



    public void addElektro(){
        elektro.add(new Elektro(25,"Sluchatka", true, Typ.APPLIANCE, "5 W/h"));
        elektro.add(new Elektro(55,"Pracka", true, Typ.APPLIANCE, "20 W/h"));
    }

    public void addObleceni(){
        obleceni.add(new Obleceni(22,"Dziny", true, Typ.CLOTHING, "Denim"));
        obleceni.add(new Obleceni(20,"Tricko", true, Typ.CLOTHING, "cotton"));
    }

    public void addPotraviny(){
        jidlo.add(new Food(12,"Milk", true, Typ.FOOD, "15.9.2025"));
        jidlo.add(new Food(20,"Meat", true, Typ.FOOD, "15.9.2025"));
    }

    public static Predmet Search(String name) {
        for (Food f : getJidlo()) {
            if (f.getName().equalsIgnoreCase(name)) {
                return f;
            }
        }
        for (Obleceni o : getObleceni()) {
            if (o.getName().equalsIgnoreCase(name)) {
                return o;
            }
        }
        for (Elektro e : getElektro()) {
            if (e.getName().equalsIgnoreCase(name)) {
                return e;
            }
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
