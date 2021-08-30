package org.sfeir.springsecurityschool.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Stream;

@Component
@Slf4j
public class CustomAccessDecisionManager implements AccessDecisionManager {

  public static final String SCOPE_PREFIX = "SCOPE_";
  private RoutesProvider routesProvider;

  public CustomAccessDecisionManager(RoutesProvider routesProvider) {
    this.routesProvider = routesProvider;
  }

  @Override
  public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
    log.debug("Decide authentication : authentication {}, object {}, configAttribute {}", authentication, object, configAttributes);
    FilterInvocation filterInvocation = (FilterInvocation) object;
    if (isOpenUrl(filterInvocation)) {
      return;
    }
    if (authentication.getPrincipal() instanceof DefaultOAuth2AuthenticatedPrincipal) {

    } else {
      if (authentication instanceof AnonymousAuthenticationToken) {
        throw new AccessDeniedException("Authorization is missing");
      } else {
        log.warn(
          "authentication type is {} should be DefaultOAuth2AuthenticatedPrincipal",
          authentication.getPrincipal().getClass());
        throw new AccessDeniedException("Access is denied");
      }


    }
  }

  private void handleCustomAuthorization(Authentication authentication, FilterInvocation filterInvocation) {
    OAuth2AuthenticatedPrincipal auth2AuthenticatedPrincipal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
    log.debug("Matching url to route with data {}/{}", filterInvocation.getRequest().getMethod(),
      filterInvocation.getRequestUrl());
    String scopeFromRoute = null;
    try {
      scopeFromRoute = routesProvider.getMatchingRouteFromUrl(filterInvocation.getRequest().getMethod(),
          filterInvocation.getRequestUrl())
        .map(Route::getScope)
        .orElseThrow(() -> new AccessDeniedException("Missing route"));
    } catch (UnknownRouteException e) {
      throw new AccessDeniedException("Missing route", e);
    }
    log.debug("Found scope {} for route", scopeFromRoute);
    String finalScopeFromRoute = scopeFromRoute;
    boolean hasScope = getScopes(auth2AuthenticatedPrincipal).anyMatch(s -> StringUtils.equals(s, SCOPE_PREFIX + finalScopeFromRoute));
    if (!hasScope) {
      log.debug("Scope {} not found, access refused", scopeFromRoute);
      throw new AccessDeniedException("Missing scope");
    } else {
      log.debug("Scope {} found, access granted", scopeFromRoute);
    }
  }

  private Stream<String> getScopes(OAuth2AuthenticatedPrincipal auth2AuthenticatedPrincipal) {
    return auth2AuthenticatedPrincipal.getAuthorities()
      .stream()
      .map(GrantedAuthority::getAuthority);
  }

  private boolean isOpenUrl(FilterInvocation filterInvocation) {
    return false;
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
