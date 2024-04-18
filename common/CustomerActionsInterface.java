package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CustomerActionsInterface extends Remote {
    public Products browseProducts() throws RemoteException;
    public User viewShoppingCart(User customer) throws RemoteException;
    public User addItemToShoppingCart(User customer, Product product) throws RemoteException;
    public User checkoutCart(User customer) throws RemoteException;
}
