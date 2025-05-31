package Store;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a product in the shop. It has a name, price, type, and stock count.
 */

public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int price;
    private final String name;
    private final Type type;
    private int stock = 1;


    public Product(int price, String name, Type type) {
        this.price = price;
        this.name = name;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
