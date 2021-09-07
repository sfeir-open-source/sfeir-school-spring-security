package com.sfeir.requestparamtokenfilter.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenAuthenticationException extends AuthenticationException {
    public TokenAuthenticationException(String s, Exception e) {
        super(s,e);
    }
}
