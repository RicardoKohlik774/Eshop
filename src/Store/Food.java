package Store;

import java.io.Serializable;
import java.util.ArrayList;

public class Food extends Predmet implements Serializable {
    private static final long serialVersionUID = 1L;

private ArrayList<Food> potraviny = new ArrayList<>();
private String trvanlivost;





    public Food(int price, String name, boolean available, Typ type, String trvanlivost) {
        super(price, name, available, type);
        this.trvanlivost = trvanlivost;
    }
}
