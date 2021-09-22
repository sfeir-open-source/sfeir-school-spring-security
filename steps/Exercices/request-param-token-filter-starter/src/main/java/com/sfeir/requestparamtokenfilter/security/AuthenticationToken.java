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
    //Non nécessaire, mais pour illustrer le process :)
    setAuthenticated(false);
    this.token = token;
  }

  @Override
  public Object getCredentials() {
    return token;
  }

  @Override
  public Object getPrincipal() {
    //TODO récuperer le sub dans le token pour valoriser le principal
  }

  /**
   * On recupere l'id de clé dans le header
   * @return
   */
  public String getKeyId() {
    return JwtHelper.headers(token).get("kid");
  }

  public void verifyToken(Jwk key) {
    try {
      //TODO initialiser l'attribut jwt :
      // Utiliser l'utilitaire Jwts
      // on doit utiliser la clé de signature (utiliser la méthode getRsaPublicKey fourni dans la classe pour convertir le JWK)
      // on parse ensuite l'object token

    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      //TODO si erreur, jeter l'exception TokenAuthenticationException
    }
  }

  public void checkIfExpired() {
    //TODO utiliser l'object jwt présent dans la classe pour vérifier la validité du token
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
