package Store;

import java.util.ArrayList;

public class Obleceni extends Predmet {

    private ArrayList<Obleceni> obleceni = new ArrayList<>();

    public ArrayList<Obleceni> getObleceni() {
        return obleceni;
    }

    public Obleceni(int price, String name, boolean available, Typ type) {
        super(price, name, available, type);
    }

}
