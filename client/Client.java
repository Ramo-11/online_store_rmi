package client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import common.Helper;
import common.MenuHandler;
import common.Product;
import common.Products;
import common.Request;
import common.User;
import common.AdminActions;
import common.CustomerActions;
import common.DataController;
import common.Authorization;

public class Client {
    List<User> admins;
    List<User> customers;
    Products products;

    AdminActions adminService;
    CustomerActions customerService;
    Authorization authService;

    CustomerController customerController;
    AdminController adminController;

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        Client clientApp = new Client();
        clientApp.start();
    }

    @SuppressWarnings("resource")
    public void start() {
        ServiceFactory serviceFactory = new ServiceFactoryImpl("rmi://in-csci-rrpc02.cs.iupui.edu:4444");
        createServices(serviceFactory);
        loadData();
        FrontController frontController = new FrontController(admins, customers, products, 
                                                            authService, adminService, customerService);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            MenuHandler.displayMainMenu();
            int userInput = Helper.getUserNumInput();
    
            try {
                String username, pin;
                User admin;
                User customer;
                switch (userInput) {
                    case 1:
                        System.out.print("Enter username: ");
                        username = scanner.nextLine();
                        System.out.print("Enter pin: ");
                        pin = scanner.nextLine();
                        System.out.println();
                        admin = frontController.authUser(Request.ADMIN_LOGIN, username, pin);
                        if (admin == null) {
                            System.out.println("Invalid username or pin");
                            break;
                        }
                        admin.setProducts(products);
                        frontController.startAdmin(admin);
                        break;
                    case 2:
                        System.out.print("Enter username: ");
                        username = scanner.nextLine();
                        System.out.print("Enter pin: ");
                        pin = scanner.nextLine();
                        System.out.println();
                        customer = frontController.authUser(Request.CUSTOMER_LOGIN, username, pin);
                        if (customer == null) {
                            System.out.println("Invalid username or pin");
                            break;
                        }
                        customer.setProducts(products);
                        frontController.startCustomer(customer);
                        break;
                    case 3:
                        System.out.print("Enter username: ");
                        username = scanner.nextLine();
                        System.out.print("Enter pin: ");
                        pin = scanner.nextLine();
                        customer = frontController.authUser(Request.CREATE_ACCOUNT, username, pin);
                        if (customer == null) {
                            System.out.println("User wasn't created successfully");
                            break;
                        }
                        customer.setProducts(products);
                        frontController.startCustomer(customer);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void createServices(ServiceFactory serviceFactory) {
        try {
            adminService = serviceFactory.createAdminService();
            customerService = serviceFactory.createCustomerService();
            authService = serviceFactory.createAuthService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        admins = (List<User>) DataController.downloadUsers("admin");
        if (admins == null) {
            admins = new ArrayList<User>();
            admins.add(new User("admin", "12345", "admin"));
            DataController.uploadUsers(admins, "Admins.txt");
        }

        customers = (List<User>) DataController.downloadUsers("customer");
        if (customers == null) {
            customers = new ArrayList<User>();
            customers.add(new User("Omar", "12345", "customer"));
            DataController.uploadUsers(customers, "Customers.txt");
        }

        products = (Products) DataController.downloadProducts();
        if (products == null) {
            products = new Products();
            products.addProduct(new Product("Apple", 1.0, "Fruit", 500));
            DataController.uploadProducts(products);
        }
    }
}
