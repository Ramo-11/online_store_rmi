package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.CustomerActions;
import common.Product;
import common.User;

public class CustomerActionsImpl extends UnicastRemoteObject implements CustomerActions {

    protected CustomerActionsImpl() throws RemoteException {
        super();
    }

    @Override
    public void browseProducts(Product productToPurchase) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'browseProducts'");
    }

    @Override
    public void addItemToShoppingCart(Product product) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addItemToShoppingCart'");
    }

    @Override
    public void purchaseFromShoppingCart() throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'purchaseFromShoppingCart'");
    }

    @Override
    public void setUser(User user) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUser'");
    }

    @Override
    public User getUser() throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUser'");
    }

    @Override
    public void downloadData() throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'downloadData'");
    }

    @Override
    public void createAccount(User user) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAccount'");
    }
    
}
