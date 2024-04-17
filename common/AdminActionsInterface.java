package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdminActionsInterface extends Remote {
    public Products getAllProducts() throws RemoteException;
    public boolean addProduct(Product product) throws RemoteException;
    public boolean removeProduct(String name) throws RemoteException;
    public boolean updateProduct(Product product) throws RemoteException;
    public boolean createAdminAccount(User admin) throws RemoteException;
    public boolean createCustomerAccount(User user) throws RemoteException;
    public boolean removeCustomerAccount(User user) throws RemoteException;
}
