package client;

import java.rmi.Naming;

import common.AdminActionsInterface;
import common.CustomerActionsInterface;
import common.Authorization;

public class ServiceFactoryImpl implements ServiceFactoryInterface {
    private String baseUrl;

    public ServiceFactoryImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public AdminActionsInterface createAdminService() throws Exception {
        return (AdminActionsInterface) Naming.lookup(baseUrl + "/RemoteAdminActions");
    }

    @Override
    public CustomerActionsInterface createCustomerService() throws Exception {
        return (CustomerActionsInterface) Naming.lookup(baseUrl + "/RemoteCustomerActions");
    }

    @Override
    public Authorization createAuthService() throws Exception {
        return (Authorization) Naming.lookup(baseUrl + "/RemoteAuthorization");
    }
}