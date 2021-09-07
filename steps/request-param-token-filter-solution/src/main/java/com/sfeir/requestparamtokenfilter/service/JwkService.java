package com.sfeir.requestparamtokenfilter.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class JwkService {

  private final RestTemplate restTemplate;

  public JwkService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  public Optional<Jwk> getKey(String keyId) {
    //would get the keys from any available provider
    return restTemplate.getForObject("https://dev-56443628.okta.com/oauth2/aus1nztlwsqX4tYHg5d7/v1/keys", Jwks.class)
      .getKeys().stream().filter(jwk -> StringUtils.equalsIgnoreCase(jwk.getKid(), keyId)).findFirst();
  }

}
