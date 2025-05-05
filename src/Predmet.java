public class Predmet {

    private int price;
    private String name;
    private boolean available;
    private Typ type;


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

}
