package Console;

import Store.Predmet;

import java.util.ArrayList;

public class User {

 private ArrayList<Predmet> kosik = new ArrayList<>();
 private ArrayList<Predmet> owned = new ArrayList<>();
 private String name;
 private String id;
 private int money;

 public ArrayList<Predmet> getKosik() {
  return kosik;
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
