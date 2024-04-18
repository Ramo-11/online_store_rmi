package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.CustomerActionsInterface;
import common.DataController;
import common.Product;
import common.Products;
import common.User;

public class CustomerActionsImpl extends UnicastRemoteObject implements CustomerActionsInterface {

    private Products products;

    protected CustomerActionsImpl() throws RemoteException {
        super();
        this.products = DataController.downloadProducts();
    }

    @Override
    public Products browseProducts() throws RemoteException {
        System.out.println("Received request to browse products\n");
        this.products = DataController.downloadProducts();
        return products;
    }

    @Override
    public User viewShoppingCart(User customer) throws RemoteException {
        System.out.println("Received request to view shopping cart");
        return customer;
    }
    
    @Override
    public User addItemToShoppingCart(User customer, Product product) throws RemoteException {
        if (customer == null || product == null) {
            System.out.println("Customer or product is null");
            return customer;
        }
        System.out.println("Received request to add product to shopping cart");
        for (Product p : products.getProducts()) {
            if (p.getName().equals(product.getName())) {
                if (p.getQuantity() < product.getQuantity()) {
                    System.out.println("Not enough stock");
                    return customer;
                }
                customer.addItemToShoppingCart(product);
                p.setQuantity(p.getQuantity() - product.getQuantity());
                DataController.uploadProducts(products);
                System.out.println("Product was added to shopping cart");
                return customer;
            }
        }
        System.out.println("Product not found");
        return customer;
    }

    @Override
    public User checkoutCart(User customer) throws RemoteException {
        System.out.println("Received request to checkout shopping cart");
        customer.getShoppingCart().clear();
        return customer;
    }
}
