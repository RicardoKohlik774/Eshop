package Commands;

import Console.Data;
import Console.Console;
import Store.Order;
import Store.Product;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Handles the checkout process to place an order.
 * It collects delivery and payment details, checks if they are valid,
 * and then creates an order if everything is correct.
 */
public class Checkout implements Command {

    /**
     * Guides the user through checking out their cart.
     * Then asks for name, address, payment method, and (optional) credit card details.
     * The user can cancel or go back at any step.
     * When the inputted information is confirmed, an order is created and the cart gets cleared.
     */
    @Override
    public String execute(Console console) {
        Scanner scanner = new Scanner(System.in);

        if (console.getLoggedUser().getCart().isEmpty()) {
            return "Your cart is empty.";
        }

        int total = 0;
        System.out.println("Your cart contains:");
        for (Product p : console.getLoggedUser().getCart()) {
            System.out.println("- " + p.getName() + " ($" + p.getPrice() + ")");
            total += p.getPrice();
        }
        System.out.println("Total: $" + total);

        System.out.print("Do you want to proceed with the checkout? (y/n): ");
        String input = scanner.nextLine().trim();
        if (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("yes")) {
            return "Checkout cancelled. You were not issued any fees";
        }

        String fullName = "";
        String street = "";
        String city = "";
        String postalCode = "";
        String paymentMethod = "";
        String cardNumber = "";
        String expirationDate;
        String cvv;

        int step = 1;

        while (step <= 7) {
            switch (step) {
                case 1: {
                    System.out.print("Enter your full name (First Last): ");
                    fullName = scanner.nextLine().trim();
                    if (fullName.equalsIgnoreCase("cancel")) {
                        return "Checkout cancelled. You were not issued any fees";
                    }
                    Pattern namePattern = Pattern.compile("^[A-Z][a-z]+ [A-Z][a-z]+$");
                    if (namePattern.matcher(fullName).matches()) {
                        step++;
                    } else {
                        System.out.println("Invalid name format. Use: First Last with capital letters.");
                        System.out.println();
                    }
                    break;
                }

                case 2: {
                    System.out.print("Enter your street and house number (e.g. Street 123): ");
                    street = scanner.nextLine().trim();
                    if (street.equalsIgnoreCase("cancel")) {
                        return "Checkout cancelled. You were not issued any fees";
                    }
                    if (street.matches("^[A-Za-z ]+\\s\\d{3}$")) {
                        step++;
                    } else {
                        System.out.println("Invalid steet and house number format.");
                        System.out.println();
                    }
                    break;
                }

                case 3: {
                    System.out.print("Enter your city (e.g. New York): ");
                    city = scanner.nextLine().trim();
                    if (city.equalsIgnoreCase("cancel")) {
                        return "Checkout cancelled. You were not issued any fees";
                    }
                    if (city.equalsIgnoreCase("back")) {
                        step--;
                        break;
                    }
                    if (city.matches("^[A-Za-z\\s-]+$")) {
                        step++;
                    } else {
                        System.out.println("Invalid city name.");
                        System.out.println();
                    }
                    break;
                }

                case 4: {
                    System.out.print("Enter your postal code (e.g. 12345 or 123 45): ");
                    postalCode = scanner.nextLine().trim();
                    if (postalCode.equalsIgnoreCase("cancel")) {
                        return "Checkout cancelled. You were not issued any fees";
                    }
                    if (postalCode.equalsIgnoreCase("back")) {
                        step--;
                        break;
                    }
                    if (postalCode.matches("^\\d{3}\\s?\\d{2}$")) {
                        step++;
                    } else {
                        System.out.println("Invalid postal code format.");
                        System.out.println();
                    }
                    break;
                }

                case 5: {
                    System.out.print("Select payment method (cash/card): ");
                    paymentMethod = scanner.nextLine().trim().toLowerCase();
                    if (paymentMethod.equalsIgnoreCase("cancel")) {
                        return "Checkout cancelled. You were not issued any fees";
                    }
                    if (paymentMethod.equalsIgnoreCase("back")) {
                        step--;
                        break;
                    }
                    if (paymentMethod.equals("cash") || paymentMethod.equals("card")) {
                        if (paymentMethod.equals("card")) {
                            step++;
                        } else {
                            step = 7;
                        }
                    } else {
                        System.out.println("Invalid payment method. Use 'cash' or 'card'.");
                        System.out.println();
                    }
                    break;
                }

                case 6: {
                    System.out.print("Enter your card number (e.g. 1234 1234 1234 1234): ");
                    cardNumber = scanner.nextLine().trim();
                    if (cardNumber.equalsIgnoreCase("cancel")) {
                        return "Checkout cancelled. You were not issued any fees";
                    }
                    if (cardNumber.equalsIgnoreCase("back")) {
                        step--;
                        break;
                    }
                    if (cardNumber.matches("^(\\d{4}[- ]?){3}\\d{4}$")) {
                        System.out.print("Enter expiration date (MM/YY or MM/YYYY): ");
                        expirationDate = scanner.nextLine().trim();
                        if (expirationDate.equalsIgnoreCase("cancel")) {
                            return "Checkout cancelled. You were not issued any fees";
                        }
                        if (expirationDate.equalsIgnoreCase("back")) {
                            continue;
                        }

                        if (!expirationDate.matches("^(0[1-9]|1[0-2])/\\d{2,4}$")) {
                            System.out.println("Invalid expiration date.");
                            System.out.println();
                            continue;
                        }

                        System.out.print("Enter CVV (3 digits): ");
                        cvv = scanner.nextLine().trim();
                        if (cvv.equalsIgnoreCase("cancel")) {
                            return "Checkout cancelled. You were not issued any fees";
                        }
                        if (cvv.equalsIgnoreCase("back")) {
                            continue;
                        }

                        if (!cvv.matches("^\\d{3}$")) {
                            System.out.println("Invalid CVV.");
                            System.out.println();
                            continue;
                        }

                        step++;
                    } else {
                        System.out.println("Invalid card number.");
                        System.out.println();
                    }
                    break;
                }

                case 7: {
                    System.out.println("\n===== ORDER SUMMARY =====");
                    System.out.println("Name: " + fullName);
                    System.out.println("Total Price: $" + total);
                    System.out.println("Delivery Address: " + street + ", " + city + ", " + postalCode);
                    System.out.println("Payment Method: " + paymentMethod);
                    if (paymentMethod.equals("card")) {
                        System.out.println("Card number: " + cardNumber);
                    }

                    System.out.print("Do you confirm this order? (y/n): ");
                    String confirm = scanner.nextLine().trim();
                    if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
                        if (paymentMethod.equals("card")) {
                            if (console.getLoggedUser().getMoney() < total) {
                                return "You do not have enough money.";
                            }
                            console.getLoggedUser().setMoney(console.getLoggedUser().getMoney() - total);
                        }
                        ArrayList<Product> cartItems = new ArrayList<>(console.getLoggedUser().getCart());
                        Order newOrder = new Order(cartItems, "This order was cancelled by an admin.");
                        newOrder.setPaymentMethod(paymentMethod);
                        newOrder.setTotalPrice(total);
                        console.getLoggedUser().addOrder(newOrder);
                        console.getLoggedUser().getCart().clear();
                        Data.save(console.getLoggedUser());
                        return "Checkout complete. Your order is being processed. Thank you";
                    } else {
                        return "Checkout cancelled. You were not issued any fees";
                    }
                }
            }
        }

        return "Unexpected error.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
