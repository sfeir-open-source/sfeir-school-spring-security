package com.sfeir.requestparamtokenfilter.exception;

import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jwt.JWTParser;
import com.sfeir.requestparamtokenfilter.service.Jwk;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

/**
 * Dummy token object
 */
public class AuthenticationToken extends AbstractAuthenticationToken {

  private final String principal;
  private final String credentials;
  private final String keyId;

  @SneakyThrows
  public AuthenticationToken(String token) {
    //read token and load roles if needed
    super(Collections.singletonList(new SimpleGrantedAuthority("USER")));
    credentials = token;
    principal = "Test User";
    //extract uuid from token
    keyId = ((JWSHeader) JWTParser.parse(token).getHeader()).getKeyID();
  }

  @Override
  public Object getCredentials() {
    return credentials;
  }

  @Override
  public Object getPrincipal() {
    return principal;
  }

  public String getKeyId() {
    return keyId;
  }

  public void verifyToken(Jwk key) {

  }

  public void checkIfExpired() {

  }
}
