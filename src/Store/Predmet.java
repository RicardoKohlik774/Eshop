package Store;

public class Predmet {

    private int price;
    private String name;
    private boolean available;
    private Typ type;
    private int stock = 1;


    public Predmet(int price, String name, boolean available, Typ type) {
        this.price = price;
        this.name = name;
        this.available = available;
        this.type = type;
    }

    public Typ getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
