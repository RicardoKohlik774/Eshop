package Store;

import java.util.ArrayList;

public class Food extends Predmet {

private ArrayList<Food> potraviny = new ArrayList<>();
private String trvanlivost;





    public Food(int price, String name, boolean available, Typ type, String trvanlivost) {
        super(price, name, available, type);
        this.trvanlivost = trvanlivost;
    }
}
