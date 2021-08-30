package org.sfeir.springsecurityschool.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class GlobalSecurity extends WebSecurityConfigurerAdapter {


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.sessionManagement().disable().csrf().disable()
      .authorizeRequests()
      .anyRequest()
      .permitAll();
  }
}
