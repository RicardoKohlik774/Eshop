package Store;

import java.util.ArrayList;

public class Elektro extends Predmet {

private ArrayList<Elektro> spotrebice = new ArrayList<>();
    private String spotreba;

    public Elektro(int price, String name, boolean available, Typ type, String spotreba) {
        super(price, name, available, type);
        this.spotreba = spotreba;
    }
}
