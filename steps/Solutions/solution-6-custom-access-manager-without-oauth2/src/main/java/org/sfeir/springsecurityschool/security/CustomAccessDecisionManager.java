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
    //handle allowing public url like actuators for example
    if (isOpenUrl(filterInvocation)) {
      return;
    }
    if (authentication instanceof UsernamePasswordAuthenticationToken) {
      handleCustomAuthorization(authentication,filterInvocation);
    } else {
      if (authentication instanceof AnonymousAuthenticationToken) {
        throw new AccessDeniedException("Authorization is missing");
      } else {
        log.warn(
          "authentication type is {} should be UsernamePasswordAuthenticationToken",
          authentication.getClass());
        throw new AccessDeniedException("Access is denied");
      }
    }

  }
  private void handleCustomAuthorization(Authentication authentication, FilterInvocation filterInvocation) {
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;
    log.debug("Matching url to route with data {}/{}", filterInvocation.getRequest().getMethod(),
      filterInvocation.getRequestUrl());
    String scopeFromRoute;
    try {
      scopeFromRoute = routesProvider.getMatchingRouteFromUrl(filterInvocation.getRequest().getMethod(),
          filterInvocation.getRequestUrl())
        .map(Route::getScope)
        .orElseThrow(() -> new AccessDeniedException("Missing route"));
    } catch (UnknownRouteException e) {
      throw new AccessDeniedException("Missing route", e);
    }
    log.info("Found scope {} for route", scopeFromRoute);
    String finalScopeFromRoute = scopeFromRoute;
    boolean hasScope = getScopes(usernamePasswordAuthenticationToken).anyMatch(s -> {
      log.debug("compare {} and {}", s, ROLE_PREFIX + finalScopeFromRoute);
      return StringUtils.equals(s, ROLE_PREFIX + finalScopeFromRoute);
    });
    if (!hasScope) {
      log.info("Scope {} not found, access refused", scopeFromRoute);
      throw new AccessDeniedException("Missing scope");
    } else {
      log.info("Scope {} found, access granted", scopeFromRoute);
    }
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
