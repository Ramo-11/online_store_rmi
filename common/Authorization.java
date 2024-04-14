package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Authorization extends Remote {
    public boolean authorize(User user) throws RemoteException;
    public boolean createAccount(User user) throws RemoteException;
}