package client;

import common.User;
import common.AdminActionsInterface;
import common.MenuHandler;
import common.Product;
import common.Products;

import java.rmi.RemoteException;
import java.util.Scanner;

@SuppressWarnings("unused")
public class AdminController {
    private User admin;
    private AdminActionsInterface adminActions;
    private Scanner scanner = new Scanner(System.in);

    public AdminController(AdminActionsInterface adminActions) {
        this.adminActions = adminActions;
    }

    public void start(User admin) {
        this.admin = admin;
        int choice;
        do {
            MenuHandler.displayAdminMenu();
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.next();
                System.out.print("Enter your choice: ");
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAllProducts();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    removeProduct();
                    break;
                case 4:
                    updateProduct();
                    break;
                case 5:
                    createAdminAccount();
                    break;
                case 6:
                    createCustomerAccount();
                    break;
                case 7:
                    removeCustomerAccount();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);
    }

    private void viewAllProducts() {
        try {
            adminActions.getAllProducts().displayProducts();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void addProduct() {
        scanner.nextLine();
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();
        try {
            if (adminActions.addProduct(new Product(name, price, description, quantity))) {
                System.out.println("Product added successfully");
            } else {
                System.out.println("Failed to add product, check server logs");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void removeProduct() {
        scanner.nextLine();
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        try {
            if (adminActions.removeProduct(name)) {
                System.out.println("Product removed successfully");
            } else {
                System.out.println("Failed to remove product, check server logs");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void updateProduct() {
        Products products;
        scanner.nextLine();
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        try {
            products = adminActions.getAllProducts();
        } catch (RemoteException e) {
            e.printStackTrace();
            System.out.println("Failed to get products, check server logs");
            return;
        }
        for (Product product : products.getProducts()) {
            if (product.getName().equals(name)) {
                System.out.print("Enter new product description: ");
                String description = scanner.nextLine();
                System.out.print("Enter new product price: ");
                double price = scanner.nextDouble();
                System.out.print("Enter new product quantity: ");
                int quantity = scanner.nextInt();
                try {
                    adminActions.updateProduct(new Product(name, price, description, quantity));
                    System.out.println("Product updated successfully");
                } catch (RemoteException e) {
                    e.printStackTrace();
                    System.out.println("Failed to update product, check server logs");
                }
                return;
            }
        }
        System.out.println("Product not found");
    }

    public void createAdminAccount() {
        scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter pin: ");
        String pin = scanner.nextLine();
        try {
            if (adminActions.createAdminAccount(new User(username, pin, "admin"))) {
                System.out.println("Admin account created successfully");
            } else {
                System.out.println("Failed to create admin account, check server logs");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void createCustomerAccount() {
        scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter pin: ");
        String pin = scanner.nextLine();
        try {
            if (adminActions.createCustomerAccount(new User(username, pin, "customer"))) {
                System.out.println("Customer account created successfully");
            } else {
                System.out.println("Failed to create customer account, check server logs");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void removeCustomerAccount() {
        scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        try {
            if (adminActions.removeCustomerAccount(new User(username, "", "customer"))) {
                System.out.println("Customer account removed successfully");
            } else {
                System.out.println("Failed to remove customer account, check server logs");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
