package client;

import common.Request;
import common.User;
import common.Products;
import common.AdminActions;
import common.CustomerActions;

import common.Authorization;

import java.rmi.RemoteException;
import java.util.List;

public class FrontController {
    private AdminController adminController;
    private CustomerController customerController;
    private Authorization authorization;

    public FrontController(List<User> admins, List<User> customers, Products products, 
                            Authorization authService, AdminActions adminService, CustomerActions customerService) {
        this.adminController = new AdminController(adminService);
        this.customerController = new CustomerController(customerService);
        this.authorization = authService;
    }

    public User authUser(Request requestType, String username, String pin) throws RemoteException {  
        switch (requestType) {
            case CUSTOMER_LOGIN:
                User customerToLogIn = new User(username, pin, "customer");
                if (authorization.authorize(customerToLogIn)) { 
                    return customerToLogIn;
                }
                return null;
            case ADMIN_LOGIN:
                User adminToLogIn = new User(username, pin, "admin");
                if (authorization.authorize(adminToLogIn)) {
                    return adminToLogIn;
                }
                return null;
            case CREATE_ACCOUNT:
                User customerToCreate = new User(username, pin, "customer");
                if (authorization.createAccount(customerToCreate)) {
                    return customerToCreate;
                }
                return null;
            default:
                System.out.println("Error in FrontController.authUser: invalid request type");
                return null;
        }
    }

    public void startAdmin(User admin) {
        adminController.start(admin);
    }
    
    public void startCustomer(User customer) {
        customerController.start(customer);
    }
}
