package Store;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {        //BASIC IDEA FOR THE ORDER LOGIC
    private static final long serialVersionUID = 1L;
    private ArrayList<Predmet> items;
    private int neeededLogins;

    public Order(ArrayList<Predmet> items) {
        this.items = new ArrayList<>(items);
        this.neeededLogins = 3;
    }



    public void minusLogin() {
        if (neeededLogins > 0) {
            neeededLogins--;
        }
    }

    public boolean isReady() {
        return neeededLogins <= 0;
    }

    public ArrayList<Predmet> getItems() {
        return items;
    }
}
