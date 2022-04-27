package org.sfeir.springsecurityschool.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sébastien DE SANTIS, SFEIR Benelux
 * @version 1.0
 * @since 4/21/22
 */
public class RequestFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
    var header = httpServletRequest.getHeader("magic-number");
    if(header==null || !header.equalsIgnoreCase("42")){
      httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
      return;
    }
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
