package jocture.factory.factory;

import jocture.factory.client.LoginType;
import jocture.factory.service.LoginService;

import java.util.List;
import java.util.NoSuchElementException;

// @Component
public class LoginServiceFactoryV2 implements LoginServiceFactory {

    private final List<LoginService> loginServices;

    public LoginServiceFactoryV2(List<LoginService> loginServices) {
        this.loginServices = loginServices;
    }

    @Override
    public LoginService find(LoginType loginType) {
        return loginServices.stream()
            .filter(service -> service.supports(loginType))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("Cannot find LoginService of " + loginType));
    }
}
