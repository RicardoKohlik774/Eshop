package Console;

import Store.Order;
import Store.Predmet;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

 private static final long serialVersionUID = 1L;

 private ArrayList<Predmet> cart = new ArrayList<>();
 private ArrayList<ArrayList<Predmet>> owned = new ArrayList<>();
 private String name;
 private String id;
 private int money;
 private ArrayList<Order> orders = new ArrayList<>();
 private int loginCount = 0;

 public User(String id) {
  this.id = id;
 }

 public void login() {
  loginCount++;
  ArrayList<Order> completed = new ArrayList<>();

  for (Order order : orders) {
   order.tickLogin();
  }
  for (Order order : orders) {
   if (order.isReady()) {
    owned.add(order.getItems());
    completed.add(order);
   }
  }
  orders.removeAll(completed);
 }

 public void addOrder(Order o) {
  orders.add(o);
 }

 public void addToCart(Predmet predmet) {
  cart.add(predmet);
 }

 public ArrayList<Predmet> getCart() {
  return cart;
 }

 public ArrayList<ArrayList<Predmet>> getOwned() {
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
  this.money = money;
 }

 public int getLoginCount() {
  return loginCount;
 }

 public ArrayList<Order> getOrders() {
  return orders;
 }
}
