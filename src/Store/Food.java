package Store;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a grocery item in the shop.
 */
public class Food extends Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public Food(int price, String name, Type type) {
        super(price, name, type);

    }
}
