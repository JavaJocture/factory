package jocture.factory.factory;

import jocture.factory.client.LoginType;
import jocture.factory.service.LoginService;

import java.util.*;

// @Component
public class LoginServiceFactoryV3 implements LoginServiceFactory {

    private final List<LoginService> loginServices;

    public LoginServiceFactoryV3(List<LoginService> loginServices) {
        this.loginServices = loginServices;
    }

    @Override
    public LoginService find(LoginType loginType) {
        return LoginServiceCache.get(loginType)
            .orElseGet(() -> {
                LoginService loginService = loginServices.stream()
                    .filter(service -> service.supports(loginType))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Cannot find LoginService of " + loginType));
                LoginServiceCache.put(loginType, loginService);
                return loginService;
            });
    }

    private static class LoginServiceCache {

        private static final Map<LoginType, LoginService> cachedLoginServiceMap = new EnumMap<>(LoginType.class);

        public static Optional<LoginService> get(LoginType loginType) {
            return Optional.ofNullable(cachedLoginServiceMap.get(loginType));
        }

        public static void put(LoginType loginType, LoginService loginService) {
            cachedLoginServiceMap.put(loginType, loginService);
        }
    }

}
