package jocture.factory.service;

import jocture.factory.client.LoginType;

public interface LoginService {

    boolean supports(LoginType loginType);
    void login();
}
