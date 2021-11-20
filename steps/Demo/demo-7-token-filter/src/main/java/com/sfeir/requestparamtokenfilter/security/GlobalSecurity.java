package com.sfeir.requestparamtokenfilter.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class GlobalSecurity extends WebSecurityConfigurerAdapter {

  private JwtTokenFilter jwtTokenFilter;

  public GlobalSecurity(JwtTokenFilter jwtTokenFilter) {
    this.jwtTokenFilter = jwtTokenFilter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.addFilterAfter(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
      .authorizeRequests()
      .antMatchers("/hello").authenticated();
  }
}
