package org.sfeir.springsecurityschool.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.sfeir.springsecurityschool.exceptions.UnknownRouteException;
import org.sfeir.springsecurityschool.route.Route;
import org.sfeir.springsecurityschool.route.RoutesProvider;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Stream;

@Component
@Slf4j
public class CustomAccessDecisionManager implements AccessDecisionManager {

  public static final String ROLE_PREFIX = "ROLE_";
  private final RoutesProvider routesProvider;

  public CustomAccessDecisionManager(RoutesProvider routesProvider) {
    this.routesProvider = routesProvider;
  }
  @Override
  public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
    log.debug("Decide authentication : authentication {}, object {}, configAttribute {}", authentication, object, configAttributes);
    FilterInvocation filterInvocation = (FilterInvocation) object;
    //TODO gérer le cas de l'url /route qui est public
    if (authentication instanceof UsernamePasswordAuthenticationToken) {
      handleCustomAuthorization(authentication,filterInvocation);
    } else {
      //TODO gérer le cas ou on n'a pas l'authentication requise
    }

  }
  private void handleCustomAuthorization(Authentication authentication, FilterInvocation filterInvocation) {
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;
    log.debug("Matching url to route with data {}/{}", filterInvocation.getRequest().getMethod(),
      filterInvocation.getRequestUrl());
    String scopeFromRoute;
    //TODO récupérer le scope associé à la route du RouteProvider. Refuser l'accès si la route n'est pas trouvé avec le message "Missing Route".


    log.info("Found scope {} for route", scopeFromRoute);
    String finalScopeFromRoute = scopeFromRoute;
    boolean hasScope = getScopes(usernamePasswordAuthenticationToken).anyMatch(s -> {
      log.debug("compare {} and {}", s, ROLE_PREFIX + finalScopeFromRoute);
      return StringUtils.equals(s, ROLE_PREFIX + finalScopeFromRoute);
    });
    //TODO si on a le scope, autoriser l'accès, sinon le refuser avec le message "Missing Scope"
  }

  private Stream<String> getScopes(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
    return usernamePasswordAuthenticationToken.getAuthorities()
      .stream()
      .map(GrantedAuthority::getAuthority);
  }

  private boolean isOpenUrl(FilterInvocation filterInvocation) {
    log.info("isOpenUrl ? compare{} to{}", filterInvocation.getRequestUrl(), "/route");
    //authorize the adding route
    return StringUtils.equals(filterInvocation.getRequestUrl(), "/route");
  }
  @Override
  public boolean supports(ConfigAttribute configAttribute) {
    return true;
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return FilterInvocation.class.isAssignableFrom(aClass);
  }
}
