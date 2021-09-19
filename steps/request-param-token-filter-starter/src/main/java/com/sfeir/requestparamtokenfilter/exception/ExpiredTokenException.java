package com.sfeir.requestparamtokenfilter.exception;

import org.springframework.security.core.AuthenticationException;

public class ExpiredTokenException extends AuthenticationException {
    public ExpiredTokenException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ExpiredTokenException(String msg) {
        super(msg);
    }
}
