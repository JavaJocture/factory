package jocture.factory.factory;

import jocture.factory.client.LoginType;
import jocture.factory.service.*;

import java.util.NoSuchElementException;

// @Component
public class LoginServiceFactoryV1 implements LoginServiceFactory {

    private final WebLoginService webLoginService;
    private final MobileLoginService mobileLoginService;
    private final GoogleLoginService googleLoginService;

    public LoginServiceFactoryV1(
        WebLoginService webLoginService,
        MobileLoginService mobileLoginService,
        GoogleLoginService googleLoginService
    ) {
        this.webLoginService = webLoginService;
        this.mobileLoginService = mobileLoginService;
        this.googleLoginService = googleLoginService;
    }

    @Override
    public LoginService find(LoginType loginType) {
        if (loginType == LoginType.WEB) {
            return webLoginService;
        } else if (loginType == LoginType.MOBILE) {
            return mobileLoginService;
        } else if (loginType == LoginType.GOOGLE) {
            return googleLoginService;
        } else {
            throw new NoSuchElementException("Cannot find LoginService of " + loginType);
        }
    }
}
