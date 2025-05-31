package Console;

import Store.Order;
import Store.Product;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents the user entity.
 * User has an id, money, a cart, a list of owned products, and order history.
 */
public class User implements Serializable {

 @Serial
 private static final long serialVersionUID = 1L;

 private final ArrayList<Product> cart = new ArrayList<>();
 private final ArrayList<ArrayList<Product>> owned = new ArrayList<>();
 private final String id;
 private int money;
 private final ArrayList<Order> orders = new ArrayList<>();

 /**
  * Creates a new user with the given id and sets default money to 1000.
  */
 public User(String id) {
  this.id = id;
  this.money = 1000;
 }

 /**
  * Adds a new order to this user's order history.
  */
 public void addOrder(Order o) {
  orders.add(o);
 }

 /**
  * Adds a product to the user's cart.
  */
 public void addToCart(Product product) {
  cart.add(product);
 }

 public ArrayList<Product> getCart() {
  return cart;
 }

 public ArrayList<ArrayList<Product>> getOwned() {
  return owned;
 }

 public String getId() {
  return id;
 }

 public int getMoney() {
  return money;
 }

 /**
  * Sets the user's money.
  * Handles so that it cannot go below 0.
  */
 public void setMoney(int money) {
  this.money = Math.max(money, 0);
 }

 public ArrayList<Order> getOrders() {
  return orders;
 }
}
