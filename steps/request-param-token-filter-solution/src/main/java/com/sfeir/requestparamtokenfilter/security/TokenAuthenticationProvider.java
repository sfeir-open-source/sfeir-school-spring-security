package com.sfeir.requestparamtokenfilter.security;

import com.sfeir.requestparamtokenfilter.exception.AuthenticationToken;
import com.sfeir.requestparamtokenfilter.exception.ExpiredTokenException;
import com.sfeir.requestparamtokenfilter.exception.KeyException;
import com.sfeir.requestparamtokenfilter.exception.TokenAuthenticationException;
import com.sfeir.requestparamtokenfilter.service.Jwk;
import com.sfeir.requestparamtokenfilter.service.JwkService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private JwkService jwkService;

  public TokenAuthenticationProvider(JwkService jwkService) {
    this.jwkService = jwkService;
  }

  @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AuthenticationToken token = (AuthenticationToken) authentication;
        try {
            Jwk key = jwkService.getKey(token.getKeyId()).orElseThrow(KeyException::new);
            token.verifyToken(key);
            token.checkIfExpired();
        } catch (ExpiredTokenException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new TokenAuthenticationException("Can't verify signature of token", e);
        }
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AuthenticationToken.class.isAssignableFrom(authentication);
    }
}
