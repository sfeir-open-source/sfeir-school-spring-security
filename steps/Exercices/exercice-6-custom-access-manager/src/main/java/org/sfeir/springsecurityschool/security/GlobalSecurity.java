package org.sfeir.springsecurityschool.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class GlobalSecurity extends WebSecurityConfigurerAdapter {



  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
      .authorizeRequests()
      //TODO ajouter l' access decision manager
      .anyRequest().authenticated()
      .and()
      .exceptionHandling()
      .accessDeniedPage("/error")
      .and()
      .oauth2ResourceServer().jwt();
  }
}
