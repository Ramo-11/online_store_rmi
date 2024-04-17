package server;

import java.util.List;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.AdminActionsInterface;
import common.DataController;
import common.Product;
import common.Products;
import common.User;

public class AdminActionsImpl extends UnicastRemoteObject implements AdminActionsInterface {

    private Products products;
    private List<User> admins;
    private List<User> customers;

    protected AdminActionsImpl() throws RemoteException {
        super();
        this.products = DataController.downloadProducts();
        this.admins = DataController.downloadUsers("admin");
        this.customers = DataController.downloadUsers("customer");
    }

    @Override
    public Products getAllProducts() throws RemoteException {
        System.out.println("Received request to get all products\n");
        return products;
    }

    @Override
    public boolean addProduct(Product product) throws RemoteException {
        System.out.println("Received request to add a product\n");
        products.addProduct(product);
        DataController.uploadProducts(products);
        return true;
    }

    @Override
    public boolean removeProduct(String name) throws RemoteException {
        System.out.println("Received request to remove a product\n");
        for (Product product : products.getProducts()) {
            if (product.getName().equals(name)) {
                products.removeProduct(product);
                DataController.uploadProducts(products);
                System.out.println("Product removed successfully");
                return true;
            }
        }
        System.out.println("Product not found");
        return false;
    }

    @Override
    public boolean updateProduct(Product product) throws RemoteException {
        removeProduct(product.getName());
        addProduct(product);
        System.out.println("Product updated successfully");
        return true;
    }

    @Override
    public boolean createAdminAccount(User admin) throws RemoteException {
        for (User user : this.admins) {
            if (user.getName().equals(admin.getName())) {
                System.out.println("Admin already exists");
                return false;
            }
        }
        this.admins.add(admin);
        DataController.uploadUsers(admins, "Admins.txt");
        System.out.println("Admin account created successfully");
        return true;
    }

    @Override
    public boolean createCustomerAccount(User customer) throws RemoteException {
        for (User user : this.customers) {
            if (user.getName().equals(customer.getName())) {
                System.out.println("Customer already exists");
                return false;
            }
        }
        this.customers.add(customer);
        DataController.uploadUsers(customers, "Customers.txt");
        System.out.println("Customer account created successfully");
        return true;
    }

    @Override
    public boolean removeCustomerAccount(User customer) throws RemoteException {
        User customerToRemove = null;
        for (User user : this.customers) {
            if (user.getName().equals(customer.getName())) {
                customerToRemove = user;
                break;
            }
        }
        if (customerToRemove == null) {
            System.out.println("Customer doesn't exist");
            return false;
        }
        this.customers.remove(customerToRemove);
        DataController.uploadUsers(customers, "Customers.txt");
        System.out.println("Customer account removed successfully");
        return true;
    }
    
}
