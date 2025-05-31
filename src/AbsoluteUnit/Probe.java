package AbsoluteUnit;

import Store.*;
import Console.User;
import Console.Data;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Unit tests for verifying application behavior such as:
 * serialization, cart-to-order flow, owned item updates, and stock searching.
 */
public class Probe {

    /**
     * Tests whether a user and their cart are correctly serialized and deserialized.
     */
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

    /**
     * Tests a full user order cycle: adding to cart, placing an order,
     * deducting money, moving shipping order to owned, and verifying via serialization.
     */
    @Test
    public void testFullOrderFlowWithSerialization() {
        User user = new User("test");
        Product p1 = new Product(300, "Keyboard", Type.ELECTRO);
        Product p2 = new Product(200, "Sneakers", Type.CLOTHING);
        user.addToCart(p1);
        user.addToCart(p2);
        Order order = new Order(user.getCart(), "Card");
        order.setPaymentMethod("card");
        order.setStatus("shipping");
        order.setTotalPrice(order.getTotalPrice());
        user.setMoney(user.getMoney() - order.getTotalPrice());
        user.getOrders().add(order);
        user.getCart().clear();
        Data.save(user);
        User loaded = Data.load("test");

        for (int i = 0; i < loaded.getOrders().size(); i++) {
            Order o = loaded.getOrders().get(i);
            if ("shipping".equals(o.getStatus())) {
                loaded.getOwned().add(o.getItems());
                loaded.getOrders().remove(i--);
            }
        }
        Data.save(loaded);
        User finalLoaded = Data.load("test");
        assertEquals(1000 - 500, finalLoaded.getMoney());
        assertEquals(0, finalLoaded.getCart().size());
        assertEquals(0, finalLoaded.getOrders().size());
        assertEquals(1, finalLoaded.getOwned().size());
        assertEquals(2, finalLoaded.getOwned().getFirst().size());
        assertEquals("Keyboard", finalLoaded.getOwned().getFirst().get(0).getName());
        assertEquals("Sneakers", finalLoaded.getOwned().getFirst().get(1).getName());
    }

    /**
     * Tests whether the total price is correctly deducted from the user's balance when ordering.
     */
    @Test
    public void testMoneyDeductionOnOrder() {
        User user = new User("test");
        Product p1 = new Product(300, "Mouse", Type.ELECTRO);
        Product p2 = new Product(200, "Trousers", Type.CLOTHING);
        user.addToCart(p1);
        user.addToCart(p2);

        int total = p1.getPrice() + p2.getPrice();
        Order order = new Order(user.getCart(), "Payment via card");
        order.setPaymentMethod("card");
        order.setStatus("confirmed");
        order.setTotalPrice(order.getTotalPrice());

        user.setMoney(user.getMoney() - order.getTotalPrice());

        assertEquals(500, order.getTotalPrice());
        assertEquals(1000 - total, user.getMoney());
        assertEquals("confirmed", order.getStatus());
    }

    /**
     * Tests whether serialization correctly saves and loads multiple orders and owned product lists.
     */
    @Test
    public void testSerializationWithMultipleOrdersAndOwned() {
        User user = new User("serializeTest");
        Product p1 = new Product(100, "Book", Type.FOOD); // Treated as a cookbook
        Product p2 = new Product(500, "Monitor", Type.ELECTRO);
        Product p3 = new Product(250, "Jacket", Type.CLOTHING);

        ArrayList<Product> ownedSet1 = new ArrayList<>();
        ownedSet1.add(p1);
        ownedSet1.add(p2);
        user.getOwned().add(ownedSet1);

        ArrayList<Product> orderItems = new ArrayList<>();
        orderItems.add(p3);
        Order order = new Order(orderItems, "Waiting");
        user.addOrder(order);

        user.setMoney(600);
        Data.save(user);

        User loaded = Data.load("serializeTest");

        assertNotNull(loaded);
        assertEquals(600, loaded.getMoney());
        assertEquals(1, loaded.getOrders().size());
        assertEquals("Jacket", loaded.getOrders().getFirst().getItems().getFirst().getName());
        assertEquals(1, loaded.getOwned().size());
        assertEquals(2, loaded.getOwned().getFirst().size());
        assertEquals("Monitor", loaded.getOwned().getFirst().get(1).getName());
    }

    /**
     * Tests whether orders with "shipping" status are correctly moved to owned items.
     */
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
