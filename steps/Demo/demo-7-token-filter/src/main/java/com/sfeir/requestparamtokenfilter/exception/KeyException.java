package com.sfeir.requestparamtokenfilter.exception;

import org.springframework.security.core.AuthenticationException;

public class KeyException extends AuthenticationException {

    public KeyException() {
        super("No valid key found");
    }
}
