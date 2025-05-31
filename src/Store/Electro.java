package Store;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a appliance item in the shop.
 */
public class Electro extends Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public Electro(int price, String name,Type type) {
        super(price, name, type);

    }
}
