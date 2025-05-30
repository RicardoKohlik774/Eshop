package AbsoluteUnit;

import Store.*;
import Console.User;
import Console.Data;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

public class Probe {

    @Test
    public void UserSerialization() {
        User original = new User("test");
        Product p = new Product(100, "Headphones", Type.ELECTRO);
        original.getCart().add(p);
        original.setMoney(800);
        Data.save(original);
        User loaded = Data.load("test");
        assertNotNull(loaded);
        assertEquals("test", loaded.getId());
        assertEquals(800, loaded.getMoney());
        boolean found = false;
        for (Product product : loaded.getCart()) {
            if ("Headphones".equals(product.getName())) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void CartToOrder() {
        User u = new User("test");
        Product p = new Product(60, "Tricko", Type.CLOTHING);
        u.getCart().add(p);
        Order order = new Order(u.getCart(), "Testing");
        u.getOrders().add(order);
        u.getCart().clear();
        assertEquals(0, u.getCart().size());
        assertEquals(1, u.getOrders().size());
        assertEquals("waiting for confirmation", u.getOrders().getFirst().getStatus());
    }



    @Test
    public void OrderCreation() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(30, "Bread", Type.FOOD));
        Order o = new Order(products, "Delivery in progress");
        assertEquals("waiting for confirmation", o.getStatus());
        assertEquals(1, o.getItems().size());
    }

    @Test
    public void StockSearch() {
        Stock.getFood().clear();
        Food milk = new Food(25, "Milk", Type.FOOD);
        Stock.getFood().add(milk);
        Product found = Stock.Search("Milk");
        assertNotNull(found);
        assertEquals("Milk", found.getName());
    }


    @Test
    public void AddToOwned() {
        User u = new User("test");
        Product p = new Product(800, "Laptop", Type.ELECTRO);
        ArrayList<Product> orderItems = new ArrayList<>();
        orderItems.add(p);
        Order order = new Order(orderItems, "Deliver ASAP");
        order.setStatus("shipping");
        u.getOrders().add(order);
        if ("shipping".equals(order.getStatus())) {
            u.getOwned().add(order.getItems());
            u.getOrders().remove(order);
        }
        assertEquals(0, u.getOrders().size());
        boolean foundLaptop = false;
        for (ArrayList<Product> productList : u.getOwned()) {
            for (Product product : productList) {
                if ("Laptop".equals(product.getName())) {
                    foundLaptop = true;
                    break;
                }
            }
            if (foundLaptop) break;
        }
        assertTrue(foundLaptop);
    }

}
