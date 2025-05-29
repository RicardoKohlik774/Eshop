package Store;

import java.io.Serial;
import java.io.Serializable;


public class Clothes extends Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public Clothes(int price, String name, Type type) {
        super(price, name, type);

    }

}
