package Commands;

import Console.Data;
import Console.Console;
import Console.User;
import Store.Order;

import java.util.ArrayList;
import java.util.Scanner;

public class Login implements Command {

    @Override
    public String execute(Console console) {           //MIGHT WANNA REWRITE THEM IF ELSE'S NGL
        Scanner scanner = new Scanner(System.in);
        String adminId = "00000000";
        while (true) {
            System.out.print("Enter your id (8-digits): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                return "Login cancelled";
            }

            if (input.matches("\\d{8}")) {
                if (input.equals(adminId)) {
                    console.setLoggedIn(true);
                    console.setAdmin(true);
                    console.setLoggedUser(null);
                    console.removeCommand("login");
                    return "Admin access granted.";
                } else {
                    User user = Data.load(input);
                    if (user == null) {
                        System.out.println("New user detected, Creating a new user...(REMEMBER YOUR ID!): ");
                        user = new User(input);
                    }
                    ArrayList<Order> remove = new ArrayList<>();
                    for (Order order : user.getOrders()) {
                        if (order.getStatus().equals("shipping")) {
                            if (order.isReadyToDeliver()) {
                                boolean delivered = false;
                                if (order.getPaymentMethod().equals("cash")) {
                                    if (user.getMoney() >= order.getTotalPrice()) {
                                        user.setMoney(user.getMoney() - order.getTotalPrice());
                                        delivered = true;
                                    } else {
                                        Order cancelled = new Order(new ArrayList<>(), "This order was automatically cancelled due to insufficient funds.");
                                        user.getOrders().add(cancelled);
                                        remove.add(order);
                                        user.setMoney(user.getMoney() - order.getTotalPrice());
                                        System.out.println("It appears you dont have enough money to pay the order. The order was canceled, you were not refunded any money.");
                                    }
                                } else {
                                    delivered = true;
                                }

                                if (delivered) {
                                    user.getOwned().add(order.getItems());
                                    remove.add(order);
                                    System.out.println("An order has been delivered and was added to your stash.");
                                }

                            } else {
                                order.setReadyToDeliver(true);
                                System.out.println("Your order is being shipped and will arrive the next time you log in.");
                            }
                        }
                    }

                    user.getOrders().removeAll(remove);
                    Data.save(user);
                    console.setLoggedIn(true);
                    console.setAdmin(false);
                    console.setLoggedUser(user);
                    console.removeCommand("login");
                    return "Access granted, welcome.";
                }
            } else {
                System.out.println("Invalid ID.");
            }
        }
    }


    @Override
    public boolean exit() {
        return false;
    }
}
