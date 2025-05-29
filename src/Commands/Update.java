package Commands;

import Console.Data;
import Console.Console;
import Console.User;
import Store.Order;

import java.util.ArrayList;
import java.util.Scanner;

public class Update implements Command {
    @Override
    public String execute(Console console) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a user ID which you want to update an order: ");
        String userId = scanner.nextLine().trim();

        User user = Data.load(userId);
        if (user == null) {
            return "User was not found.";
        }

        ArrayList<Order> orders = user.getOrders();
        if (orders.isEmpty()) {
            return "This user has no orders.";
        }

        System.out.println("User " + userId + " currently has these orders:");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println("[" + i + "] Date: " + orders.get(i).getDate() + " | Status: " + orders.get(i).getStatus());
        }

        System.out.print("Enter the index of the order to update: ");
        int index;
        try {
            index = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return "Invalid index.";
        }

        if (index < 0 || index >= orders.size()) {
            return "This index is out of range.";
        }

        System.out.print("Enter new status (shipping, cancelled): ");
        String newStatus = scanner.nextLine().trim().toLowerCase();
        if (newStatus.equals("cancelled")) {
            Order cancelledOrderNotice = new Order(new ArrayList<>(), "This order was cancelled by admin.");
            orders.set(index, cancelledOrderNotice);
            Data.save(user);
            return "Order has been cancelled";
        } else {
            orders.get(index).setStatus(newStatus);
            Data.save(user);
            return "Order status was updated successfully.";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
