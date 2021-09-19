package com.sfeir.requestparamtokenfilter.security;

import com.sfeir.requestparamtokenfilter.service.jwk.Jwk;
import io.jsonwebtoken.*;
import lombok.SneakyThrows;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.jwt.JwtHelper;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Collections;
import java.util.Date;

/**
 * School token object
 */
public class AuthenticationToken extends AbstractAuthenticationToken {

  private final String token;
  private Jws<Claims> jwt;

  @SneakyThrows
  public AuthenticationToken(String token) {
    //read token and load roles if needed
    super(Collections.singletonList(new SimpleGrantedAuthority("USER")));
    setAuthenticated(false);
    this.token = token;
  }

  @Override
  public Object getCredentials() {
    return token;
  }

  @Override
  public Object getPrincipal() {
    return jwt.getBody().getSubject();
  }

  public String getKeyId() {
    return JwtHelper.headers(token).get("kid");
  }

  public void verifyToken(Jwk key) {
      //TODO utiliser la classe Jwts pour valider la signature
  }

  public void checkIfExpired() {
  }

  /**
   * Helper to convert Jwk to RSA asked by the jsonwebtoken library
   * @param jwkKey
   * @return
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeySpecException
   */
  private RSAPublicKey getRsaPublicKey(Jwk jwkKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
    KeyFactory kf = KeyFactory.getInstance("RSA");
    BigInteger modulus = new BigInteger(1, Base64.decodeBase64URLSafe(jwkKey.getN()));
    BigInteger exponent = new BigInteger(1, Base64.decodeBase64URLSafe(jwkKey.getE()));
    return (RSAPublicKey) kf.generatePublic(new RSAPublicKeySpec(modulus, exponent));
  }
}
