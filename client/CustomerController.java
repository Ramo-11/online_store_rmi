package client;

import common.CustomerActions;
import common.User;

@SuppressWarnings("unused")
public class CustomerController {
    private CustomerActions customerActions;

    public CustomerController(CustomerActions customerActions) {
        this.customerActions = customerActions;
    }

    public void start(User admin) {
        // TODO: implement later
    }
}