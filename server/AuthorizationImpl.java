package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import common.Authorization;
import common.DataController;
import common.User;

public class AuthorizationImpl extends UnicastRemoteObject implements Authorization {

    private List<User> admins;
    private List<User> customers;

    public AuthorizationImpl(List<User> admins, List<User> customers) throws RemoteException {
        super();
        this.admins = admins;
        this.customers = customers;
    }

    @Override
    public boolean authorize(User userToLogIn) throws RemoteException {
        this.admins = DataController.downloadUsers("admin");
        this.customers = DataController.downloadUsers("customer");
        System.out.println("Got request to authorize user: " + userToLogIn.getName());
        if (userToLogIn == null || admins == null || admins.isEmpty() || customers == null || customers.isEmpty()) {
            System.out.println("Error in AuthService.authorize: user is null or users list is empty");
            return false;
        }
        if (userToLogIn.getType().equals("admin")) {
            return authorizeAdmin(userToLogIn);
        } else if (userToLogIn.getType().equals("customer")) {
            return authorizeCustomer(userToLogIn);
        } else {
            System.out.println("Error in AuthService.authorize: user type is invalid");
            return false;
        }
    }

    private boolean authorizeAdmin(User adminToLogIn) {
        for (User admin : admins) {
            if (admin.getName().equals(adminToLogIn.getName()) && admin.getAccountPin().equals(adminToLogIn.getAccountPin())) {
                System.out.println("Admin authorized: " + admin.getName());
                return true;
            }
        }
        System.out.println("\nAdmin not authorized: " + adminToLogIn.getName());
        return false;
    }

    private boolean authorizeCustomer(User customerToLogIn) {
        for (User customer : customers) {
            if (customer.getName().equals(customerToLogIn.getName()) && customer.getAccountPin().equals(customerToLogIn.getAccountPin())) {
                System.out.println("Customer authorized: " + customer.getName());
                return true;
            }
        }
        System.out.println("Customer not authorized: " + customerToLogIn.getName());
        return false;
    }

    @Override
    public boolean createAccount(User customerToCreate) throws RemoteException {
        if (customerToCreate == null || customers == null) {
            System.out.println("Error in AuthService.createAccount: user is null or users list is empty");
            return false;
        }
        customers.add(customerToCreate);
        DataController.uploadUsers(customers, "Customers.txt");
        return true;
    }
}
