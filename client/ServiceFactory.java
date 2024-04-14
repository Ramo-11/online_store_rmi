package client;

import common.AdminActions;
import common.Authorization;
import common.CustomerActions;

public interface ServiceFactory {
    AdminActions createAdminService() throws Exception;
    CustomerActions createCustomerService() throws Exception;
    Authorization createAuthService() throws Exception;
}