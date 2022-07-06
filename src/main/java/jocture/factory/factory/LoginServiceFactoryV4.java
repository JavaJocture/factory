package jocture.factory.factory;

import jocture.factory.client.LoginType;
import jocture.factory.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class LoginServiceFactoryV4 implements LoginServiceFactory {

    private final Logger log = LoggerFactory.getLogger(LoginServiceFactoryV4.class);

    private final List<LoginService> loginServices;

    public LoginServiceFactoryV4(List<LoginService> loginServices) {
        this.loginServices = loginServices;
    }

    @PostConstruct
    void postConstuct() {
        log.debug(">>> postConstuct()");
        Set<LoginType> loginTypes = EnumSet.allOf(LoginType.class);
        loginTypes.forEach(loginType -> {
            try {
                LoginServiceCache.put(loginType, getLoginService(loginType));
            } catch (NoSuchElementException e) {
                log.warn(getNotFoundMessage(loginType));
            }
        });
    }

    private String getNotFoundMessage(LoginType loginType) {
        return "Cannot find LoginService of " + loginType;
    }

    @Override
    public LoginService find(LoginType loginType) {
        return LoginServiceCache.get(loginType)
            .orElseGet(() -> {
                log.info(">>> No Cache : {}", loginType);
                LoginService loginService = getLoginService(loginType);
                LoginServiceCache.put(loginType, loginService);
                return loginService;
            });
    }

    private LoginService getLoginService(LoginType loginType) {
        return loginServices.stream()
            .filter(service -> service.supports(loginType))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException(getNotFoundMessage(loginType)));
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
