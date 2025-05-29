package Store;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {
    private final Date date;
    private final ArrayList<Product> items;
    private String status;
    private boolean readyToDeliver = false;
    private int totalPrice;
    private String paymentMethod;

    public Order(ArrayList<Product> kosik, String msg) {
        this.date = new Date();
        this.items = new ArrayList<>(kosik);
        this.status = "waiting for confirmation";
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Product> getItems() {
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

    public int getTotalPrice() {
        for (Product p : items) {
            totalPrice += p.getPrice();
        }
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void print() {
        System.out.println("Date: " + date);
        System.out.println("Status: " + status);
        System.out.println("Items:");
        for (Product p : items) {
            System.out.println("- " + p.getName() + " | " + p.getType() + " | " + p.getPrice() + " czk");
        }
    }
}
