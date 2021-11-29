package com.sfeir.requestparamtokenfilter.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    public static final String TOKEN = "token";
    public static final String OAUTH_AUTHORIZE_URI = "/hello";
    private final TokenAuthenticationProvider tokenAuthenticationProvider;

    public JwtTokenFilter(TokenAuthenticationProvider tokenAuthenticationProvider) {
        this.tokenAuthenticationProvider = tokenAuthenticationProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (StringUtils.isNotBlank(request.getParameter(TOKEN))) {
            AuthenticationToken token = new AuthenticationToken(request.getParameter(TOKEN));
            Authentication auth = tokenAuthenticationProvider.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getRequestURI().startsWith(OAUTH_AUTHORIZE_URI);
    }
}
