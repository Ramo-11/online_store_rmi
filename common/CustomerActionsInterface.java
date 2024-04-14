package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

// import client.ShoppingCart;
// import server.Inventory;

public interface CustomerActionsInterface extends Remote {
    public void browseProducts(Product productToPurchase) throws RemoteException;
    // public ShoppingCart viewShoppingCart() throws RemoteException;
    public void addItemToShoppingCart(Product product) throws RemoteException;
    public void purchaseFromShoppingCart() throws RemoteException;
    // public void setInventory(Inventory inventory) throws RemoteException;
    // public Inventory getInventory() throws RemoteException;
    public void setUser(User user) throws RemoteException;
    public boolean isLoginSuccessful(User currentUser) throws RemoteException;
    public User getUser() throws RemoteException;
    public void downloadData() throws RemoteException;
    public void createAccount(User user) throws RemoteException;
}
