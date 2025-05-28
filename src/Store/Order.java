package Store;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {
    private final Date date;
    private final ArrayList<Predmet> items;
    private String status;
    private boolean readyToDeliver = false;

    public Order(ArrayList<Predmet> kosik, String msg) {
        this.date = new Date();
        this.items = new ArrayList<>(kosik);
        this.status = "waiting for confirmation";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Predmet> getItems() {
        return items;
    }

    public Date getDate() {
        return date;
    }

    public boolean isReadyToDeliver() {
        return readyToDeliver;
    }

    public void setReadyToDeliver(boolean readyToDeliver) {
        this.readyToDeliver = readyToDeliver;
    }

    public void print() {
        System.out.println("Date: " + date);
        System.out.println("Status: " + status);
        System.out.println("Items:");
        for (Predmet p : items) {
            System.out.println("- " + p.getName() + " | " + p.getType() + " | " + p.getPrice() + " czk");
        }
    }
}
