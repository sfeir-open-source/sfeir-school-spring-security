package org.sfeir.springsecurityschool.route;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.sfeir.springsecurityschool.exceptions.UnknownRouteException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class RoutesProvider {

  @Getter
  public Set<Route> routes;

  public RoutesProvider() {
    this.routes = new HashSet<>();
    this.routes.add(Route.builder().method("GET").uri("/hello").scope("read").build());
    this.routes.add(Route.builder().method("GET").uri("/time").scope("read").build());
  }

  public Optional<Route> getMatchingRouteFromUrl(String method, String requestUrl) throws UnknownRouteException {
    log.info("find the matching route from url {} and method{}", requestUrl, method);
    return routes.stream()
      .peek(route -> log.info("route method{} uri{}", route.getMethod(), route.getUri()))
      .filter(route -> route.isUri(requestUrl))
      .filter(route -> route.isMethod(method)).findFirst();
  }

  public void addRoute(Route route){
    routes.add(route);
  }

}
