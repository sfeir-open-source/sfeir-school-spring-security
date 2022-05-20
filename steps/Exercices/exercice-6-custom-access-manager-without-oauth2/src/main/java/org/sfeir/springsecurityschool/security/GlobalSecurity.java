package org.sfeir.springsecurityschool.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class GlobalSecurity extends WebSecurityConfigurerAdapter {

  private final CustomAccessDecisionManager customAccessDecisionManager;

  public GlobalSecurity(CustomAccessDecisionManager customAccessDecisionManager) {
    this.customAccessDecisionManager = customAccessDecisionManager;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
     http.sessionManagement().disable().csrf().disable().authorizeRequests()
       //TODO ajouter l' access decision manager
        .anyRequest().authenticated().and().httpBasic()
        .and()
        .exceptionHandling()
        .accessDeniedPage("/error");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
      .withUser(User.withUsername("jojo").password("test").roles("read"))
      .withUser(User.withUsername("dire").password("test").roles("nop"));
  }
  @Bean
  public PasswordEncoder passwordEncoder(){
    return NoOpPasswordEncoder.getInstance();
  }
}
