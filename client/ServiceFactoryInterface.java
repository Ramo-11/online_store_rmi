package client;

import common.AdminActionsInterface;
import common.CustomerActionsInterface;
import common.Authorization;

public interface ServiceFactoryInterface {
    AdminActionsInterface createAdminService() throws Exception;
    CustomerActionsInterface createCustomerService() throws Exception;
    Authorization createAuthService() throws Exception;
}