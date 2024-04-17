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
        return products;
    }
    
    @Override
    public User addItemToShoppingCart(User customer, Product product) throws RemoteException {
        System.out.println("Received request to add product to shopping cart");
        for (Product p : products.getProducts()) {
            if (p.getName().equals(product.getName())) {
                if (p.getQuantity() < product.getQuantity()) {
                    System.out.println("Not enough stock");
                    return null;
                }
                customer.addItemToShoppingCart(product);
                System.out.println("Product was added to shopping cart");
                return customer;
            }
        }
        System.out.println("Product not found");
        return null;
    }

    @Override
    public User checkoutCart(User customer) throws RemoteException {
        System.out.println("Received request to checkout shopping cart");
        customer.getShoppingCart().clear();
        return customer;
    }
}
