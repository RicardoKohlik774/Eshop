package Store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Represents an order made by a user.
 * It contains products, status, total price, and other info.
 */
public class Order implements Serializable {
    private final Date date;
    private final ArrayList<Product> items;
    private String status;
    private boolean readyToDeliver = false;
    private int totalPrice;
    private String paymentMethod;

    /**
     * Creates a new order from a cart.
     * The order status is set to "waiting for confirmation" at first so that the admin has to confirm the order.
     */
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

    /**
     * Returns the total price of the order.
     * Adds up prices of all items.
     */
    public int getTotalPrice() {
        int total = 0;
        for (Product p : items) {
            total += p.getPrice();
        }
        return total;
    }
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Prints order info like date, status, and products in the order.
     */
    public void print() {
        System.out.println("Date: " + date);
        System.out.println("Status: " + status);
        System.out.println("Items:");
        for (Product p : items) {
            System.out.println("- " + p.getName() + " | " + p.getType() + " | " + p.getPrice() + " czk");
        }
    }
}
