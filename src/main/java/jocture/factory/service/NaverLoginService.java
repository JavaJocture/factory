package jocture.factory.service;

import jocture.factory.client.LoginType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NaverLoginService implements LoginService {

    private final Logger log = LoggerFactory.getLogger(NaverLoginService.class);

    @Override
    public boolean supports(LoginType loginType) {
        return loginType == LoginType.NAVER;
    }

    @Override
    public void login() {
        log.info(">>> login()");
    }
}
