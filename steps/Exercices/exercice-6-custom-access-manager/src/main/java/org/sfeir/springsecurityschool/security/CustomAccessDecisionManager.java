package org.sfeir.springsecurityschool.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;
import java.util.stream.Stream;


@Slf4j
public class CustomAccessDecisionManager {

  public static final String SCOPE_PREFIX = "SCOPE_";

  @Override
  public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
    log.debug("Decide authentication : authentication {}, object {}, configAttribute {}", authentication, object, configAttributes);
    FilterInvocation filterInvocation = (FilterInvocation) object;
    //TODO gérer le cas de l'url /route qui est public

    //on vérifie qu'on est sur le bon type d'object authentication
    if (authentication instanceof JwtAuthenticationToken) {
      handleCustomAuthorization(authentication,filterInvocation);
    } else {
      //TODO gérer le cas ou on n'a pas l'authentication requise
      //hint regarder la javadoc pour voir comment refuser un access
    }
  }

  /**
   * Méthode qui gere notre accès custom
   * @param authentication
   * @param filterInvocation
   */
  private void handleCustomAuthorization(Authentication authentication, FilterInvocation filterInvocation) {
    JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
    log.debug("Matching url to route with data {}/{}", filterInvocation.getRequest().getMethod(),
      filterInvocation.getRequestUrl());

    String scopeFromRoute = null;
    //TODO récupérer le scope associé à la route du RouteProvider. Refuser l'accès si la route n'est pas trouvé avec le message "Missing Route".


    log.info("Found scope {} for route", scopeFromRoute);


    //on ajoute le prefixe scopepour matcher avec le scope présent dans spring security
    String finalScopeFromRoute = scopeFromRoute;
    boolean hasScope = getScopes(jwtAuthenticationToken).anyMatch(s -> StringUtils.equals(s, SCOPE_PREFIX + finalScopeFromRoute));
    //TODO si on a le scope, autoriser l'accès, sinon le refuser avec le message "Missing Scope"

  }

  /**
   * Recupere les scopes de l'authentication
   * @param jwtAuthenticationToken
   * @return
   */
  private Stream<String> getScopes(JwtAuthenticationToken jwtAuthenticationToken) {
    return jwtAuthenticationToken.getAuthorities()
      .stream()
      .map(GrantedAuthority::getAuthority);
  }

  @Override
  public boolean supports(ConfigAttribute attribute) {
    return true;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return FilterInvocation.class.isAssignableFrom(clazz);
  }
}
