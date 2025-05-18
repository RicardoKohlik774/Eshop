package Store;

import java.util.ArrayList;

public class Elektro extends Predmet {

private ArrayList<Elektro> spotrebice = new ArrayList<>();
    private String spotreba;

    public void addObleceni(){
       spotrebice.add(new Elektro(25,"Sluchatka", true, Typ.APPLIANCE, "5 W/h"));
        spotrebice.add(new Elektro(55,"Pracka", true, Typ.APPLIANCE, "20 W/h"));
    }

    public Elektro(int price, String name, boolean available, Typ type, String spotreba) {
        super(price, name, available, type);
        this.spotreba = spotreba;
    }
}
