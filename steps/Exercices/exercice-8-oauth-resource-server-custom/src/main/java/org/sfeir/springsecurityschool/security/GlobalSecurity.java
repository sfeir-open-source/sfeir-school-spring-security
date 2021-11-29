package org.sfeir.springsecurityschool.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class GlobalSecurity extends WebSecurityConfigurerAdapter {

  //TODO ajouter la vérification de l'audience et de l'issuer
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .antMatchers("/public").permitAll()
      .antMatchers("/secure").authenticated()
      .antMatchers("/super-secure").hasAuthority("SCOPE_admin")
      .and()
      .oauth2ResourceServer().jwt();
  }
}
