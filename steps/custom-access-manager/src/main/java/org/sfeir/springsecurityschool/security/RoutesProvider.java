package org.sfeir.springsecurityschool.security;

import lombok.Getter;
import org.sfeir.springsecurityschool.exceptions.UnknownRouteException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoutesProvider {

  @Getter
  public Set<Route> routes;

  public RoutesProvider() {
    this.routes = new HashSet<>();
    this.routes.add(Route.builder().method("GET").uri("/hello").scope("hello").build());
    this.routes.add(Route.builder().method("GET").uri("/time").scope("time").build());
  }

  public Optional<Route> getMatchingRouteFromUrl(String method, String requestUrl) throws UnknownRouteException {
    return routes.stream().filter(route -> route.isUri(requestUrl)).filter(route -> route.isMethod(method)).findFirst();
  }

  public void addRoute(Route route){
    routes.add(route);
  }

}
