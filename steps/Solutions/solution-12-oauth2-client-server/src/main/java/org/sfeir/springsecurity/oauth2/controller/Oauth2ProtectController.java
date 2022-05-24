package org.sfeir.springsecurity.oauth2.controller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@RestController
public class Oauth2ProtectController {

  private final WebClient webClient;

  public Oauth2ProtectController(WebClient webClient) {
    this.webClient = webClient;
  }

  @GetMapping(value = "/oauth2protect/secure")
  public String getSecure(
    @RegisteredOAuth2AuthorizedClient("articles-client-authorization-code") OAuth2AuthorizedClient authorizedClient
  ) {
    return this.webClient
      .get()
      .uri("http://127.0.0.1:8090/secure")
      .attributes(oauth2AuthorizedClient(authorizedClient))
      .retrieve()
      .bodyToMono(String.class)
      .block();
  }
  @GetMapping(value = "/oauth2protect/supersecure")
  public String getSuperSecure(
    @RegisteredOAuth2AuthorizedClient("articles-client-authorization-code") OAuth2AuthorizedClient authorizedClient
  ) {
    return this.webClient
      .get()
      .uri("http://127.0.0.1:8090/super-secure")
      .attributes(oauth2AuthorizedClient(authorizedClient))
      .retrieve()
      .bodyToMono(String.class)
      .block();
  }
  @GetMapping(value = "/oauth2protect/public")
  public String getPublic(
    @RegisteredOAuth2AuthorizedClient("articles-client-authorization-code") OAuth2AuthorizedClient authorizedClient
  ) {
    return this.webClient
      .get()
      .uri("http://127.0.0.1:8090/public")
      .attributes(oauth2AuthorizedClient(authorizedClient))
      .retrieve()
      .bodyToMono(String.class)
      .block();
  }
}
