package jocture.factory.factory;

import jocture.factory.client.LoginType;
import jocture.factory.service.LoginService;

public interface LoginServiceFactory {

    LoginService find(LoginType loginType);
}
