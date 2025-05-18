package Store;

import java.util.ArrayList;

public class Obleceni extends Predmet {
private String material;
    private ArrayList<Obleceni> obleceni = new ArrayList<>();

    public ArrayList<Obleceni> getObleceni() {
        return obleceni;
    }


    public void addObleceni(){
      obleceni.add(new Obleceni(22,"Dziny", true, Typ.CLOTHING, "Denim"));
        obleceni.add(new Obleceni(20,"Tricko", true, Typ.CLOTHING, "cotton"));
    }

    public Obleceni(int price, String name, boolean available, Typ type, String material) {
        super(price, name, available, type);
        this.material = material;
    }

}
