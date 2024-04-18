package client;

import common.User;
import common.AdminActionsInterface;
import common.CustomerActionsInterface;
import common.MenuHandler;
import common.Product;
import common.Products;

import java.rmi.RemoteException;
import java.util.Scanner;

@SuppressWarnings("unused")
public class CustomerController {
    private User customer;
    private CustomerActionsInterface customerActions;
    private Scanner scanner = new Scanner(System.in);

    public CustomerController(CustomerActionsInterface customerActions) {
        this.customerActions = customerActions;
    }

    public void start(User customer) {
        this.customer = customer;
        int choice;
        do {
            MenuHandler.displayCustomerMenu();
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.next();
                System.out.print("Enter your choice: ");
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    browseProducts();
                    break;
                case 2:
                    viewShoppingCart();
                    break;
                case 3:
                    checkoutCart();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);
    }

    private void browseProducts() {
        try {
            if (customerActions.browseProducts().getProducts().isEmpty()) {
                System.out.println("No products available");
                return;
            }
            customerActions.browseProducts().displayProducts();
            scanner.nextLine();
            System.out.println("Enter product name to add to cart");
            String name = scanner.nextLine();
            System.out.println("Enter quantity to add to cart");
            int quantity = scanner.nextInt();
            if (addItemToShoppingCart(name, quantity)) {
                System.out.println("Product added successfully");
            } else {
                System.out.println("Failed to add product, check server logs");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void viewShoppingCart() {
        try {
            customer = customerActions.viewShoppingCart(customer);
            customer.getShoppingCart().displayCart();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void checkoutCart() {
        try {
            customer = customerActions.checkoutCart(customer);
            if (customer.getShoppingCart().size() == 0) {
                System.out.println("Thanks for shopping with us");
            } else {
                System.out.println("Failed to checkout shopping cart, check server logs");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private boolean addItemToShoppingCart(String name, int quantity) {
        try {
            Product product = new Product(name, 0, "", quantity);
            customer = customerActions.addItemToShoppingCart(customer, product);
            if (customer.getShoppingCart().size() > 0) {
                return true;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }
}
