package org.sfeir.springsecurityschool.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class GlobalSecurity extends WebSecurityConfigurerAdapter {


  private CustomAccessDecisionManager customAccessDecisionManager;

  public GlobalSecurity(CustomAccessDecisionManager customAccessDecisionManager) {
    this.customAccessDecisionManager = customAccessDecisionManager;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.sessionManagement().disable().csrf().disable()
      .authorizeRequests().accessDecisionManager(customAccessDecisionManager)
      .anyRequest().authenticated()
      .and()
      .exceptionHandling()
      .accessDeniedPage("/error")
      .and()
      .oauth2ResourceServer().jwt();
  }
}
