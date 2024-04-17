package server;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import common.AdminActionsInterface;
import common.CustomerActionsInterface;
import common.Authorization;
import common.DataController;
import common.Product;
import common.Products;
import common.User;

public class Server {
    List<User> admins;
    List<User> customers;
    Products products;
    
    public static void main(String args[]) { 
        Server server = new Server();
        server.start();
    }    

    public void start() {
        loadData();
        try {
            AdminActionsInterface remoteAdminObject = new AdminActionsImpl();
            Naming.rebind("rmi://localhost:4444/RemoteAdminActions", remoteAdminObject);

            CustomerActionsInterface remoteCustomerObject = new CustomerActionsImpl();
            Naming.rebind("rmi://localhost:4444/RemoteCustomerActions", remoteCustomerObject);

            Authorization remoteAuthObject = new AuthorizationImpl(admins, customers);
            Naming.rebind("rmi://localhost:4444/RemoteAuthorization", remoteAuthObject);

            System.out.println("Server is running...");
        } catch (Exception e) {
            System.out.println("Exception caught in Server");
            e.printStackTrace();
            System.exit(-1);
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
