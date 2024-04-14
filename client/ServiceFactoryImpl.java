package client;

import java.rmi.Naming;

import common.AdminActions;
import common.CustomerActions;
import common.Authorization;

public class ServiceFactoryImpl implements ServiceFactory {
    private String baseUrl;

    public ServiceFactoryImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public AdminActions createAdminService() throws Exception {
        return (AdminActions) Naming.lookup(baseUrl + "/RemoteAdminActions");
    }

    @Override
    public CustomerActions createCustomerService() throws Exception {
        return (CustomerActions) Naming.lookup(baseUrl + "/RemoteCustomerActions");
    }

    @Override
    public Authorization createAuthService() throws Exception {
        return (Authorization) Naming.lookup(baseUrl + "/RemoteAuthorization");
    }
}