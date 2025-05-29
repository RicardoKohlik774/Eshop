package Console;

import Store.Order;
import Store.Product;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

 @Serial
 private static final long serialVersionUID = 1L;

 private final ArrayList<Product> cart = new ArrayList<>();
 private final ArrayList<ArrayList<Product>> owned = new ArrayList<>();
 private String name;    //MAYBE REGISTER THE USERS NAME? WOULD BE COOL ig?
 private final String id;
 private int money;
 private final ArrayList<Order> orders = new ArrayList<>();

 public User(String id) {
  this.id = id;
  this.money = 1000;
 }



 public void addOrder(Order o) {
  orders.add(o);
 }

 public void addToCart(Product product) {
  cart.add(product);
 }

 public ArrayList<Product> getCart() {
  return cart;
 }

 public ArrayList<ArrayList<Product>> getOwned() {
  return owned;
 }

 public String getName() {
  return name;
 }

 public String getId() {
  return id;
 }

 public int getMoney() {
  return money;
 }

 public void setMoney(int money) {
     this.money = Math.max(money, 0); //APPARENTLY DOES THE SAME AS SETTING IT TO 0 WHEN IT GOES BELOW 0
 }



 public ArrayList<Order> getOrders() {
  return orders;
 }
}
