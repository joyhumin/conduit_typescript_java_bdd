package nz.co.joyhu.service;

import org.springframework.security.core.AuthenticationException;

public class UserNotFoundException extends AuthenticationException {

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
