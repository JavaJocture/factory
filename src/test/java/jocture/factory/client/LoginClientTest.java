package jocture.factory.client;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginClientTest {

    @Autowired
    LoginClient loginClient;

    @ParameterizedTest
    @EnumSource
    void login(LoginType loginType) {
        loginClient.login(loginType);
    }

}