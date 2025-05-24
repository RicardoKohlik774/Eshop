package Commands;

import Console.Konzole;
import Store.Predmet;

import java.util.Scanner;

public class Checkout implements Command {

    @Override
    public String execute(Konzole konzole) {   //REDO THE GENERATIONAL IF ELSE STATEMENTS?
        Scanner scanner = new Scanner(System.in);

        if (konzole.getLoggedUser().getCart().isEmpty()) {
            return "Your cart is empty.";
        }

        int total = 0;
        System.out.println("Your cart contains:");
        for (Predmet p : konzole.getLoggedUser().getCart()) {
            System.out.println("- " + p.getName() + " ($" + p.getPrice() + ")");
            total += p.getPrice();
        }
        System.out.println("Total: $" + total);

        System.out.print("Do you want to proceed with the checkout? (y/n): ");
        String input = scanner.nextLine().trim();
        if (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("yes")) {
            return "Checkout cancelled. You were not issued any fees";
        }

        String street = "";
        String city = "";
        String postalCode = "";
        String paymentMethod = "";
        String cardNumber = "";
        String expirationDate = "";
        String cvv = "";

        int step = 1;

        while (step <= 6) {
            switch (step) {
                case 1: {
                    System.out.print("Enter your street and house number (e.g. Ulicka 123): ");
                    street = scanner.nextLine().trim();
                    if (street.equalsIgnoreCase("cancel")) {
                        return "Checkout cancelled. You were not issued any fees";
                    }
                    if (street.matches("^[A-Za-záčďéěíňóřšťúůýžÁČĎÉĚÍŇÓŘŠŤÚŮÝŽ ]+\\s\\d{3}$")) {
                        step++;
                    } else {
                        System.out.println("Invalid steet and house number format.");
                        System.out.println();
                    }
                    break;
                }

                case 2: {
                    System.out.print("Enter your city (e.g. Ceske Budejovice): ");
                    city = scanner.nextLine().trim();
                    if (city.equalsIgnoreCase("cancel")) {
                        return "Checkout cancelled. You were not issued any fees";
                    }
                    if (city.equalsIgnoreCase("back")) {
                        step--;
                        break;
                    }
                    if (city.matches("^[A-Za-záčďéěíňóřšťúůýžÁČĎÉĚÍŇÓŘŠŤÚŮÝŽ\\s-]+$")) {
                        step++;
                    } else {
                        System.out.println("Invalid city name.");
                        System.out.println();
                    }
                    break;
                }

                case 3: {
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

                case 4: {
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
                            step = 6;
                        }
                    } else {
                        System.out.println("Invalid payment method. Use 'cash' or 'card'.");
                        System.out.println();
                    }
                    break;
                }

                case 5: {
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

                case 6: {
                    System.out.println("\n===== ORDER SUMMARY =====");
                    System.out.println("Total Price: $" + total);
                    System.out.println("Delivery Address: " + street + ", " + city + ", " + postalCode);
                    System.out.println("Payment Method: " + paymentMethod);
                    if (paymentMethod.equals("card")) {
                        System.out.println("Card number:" + cardNumber);
                    }

                    System.out.print("Do you confirm this order? (y/n): ");
                    String confirm = scanner.nextLine().trim();
                    if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
                        return "Checkout complete. Thank you for your order.";
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
