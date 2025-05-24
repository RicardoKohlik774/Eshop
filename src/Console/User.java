package Console;

import Store.Predmet;

import java.util.ArrayList;

public class User {

 private ArrayList<Predmet> cart = new ArrayList<>();
 private ArrayList<Predmet> owned = new ArrayList<>();
 private String name;
 private String id;
 private int money;

 public User(String id) {
  this.id = id;
 }



 public void addToCart(Predmet predmet) {
  cart.add(predmet);
 }

 public ArrayList<Predmet> getCart() {
  return cart;
 }



 public ArrayList<Predmet> getOwned() {
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
}
