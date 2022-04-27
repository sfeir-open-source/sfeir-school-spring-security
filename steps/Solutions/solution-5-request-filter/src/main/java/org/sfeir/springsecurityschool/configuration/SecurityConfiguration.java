package org.sfeir.springsecurityschool.configuration;

import org.sfeir.springsecurityschool.filter.RequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Sébastien DE SANTIS, SFEIR Benelux
 * @version 1.0
 * @since 4/21/22
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.addFilterAfter(new RequestFilter(), UsernamePasswordAuthenticationFilter.class)
      .authorizeRequests()
      .anyRequest()
      .permitAll();
  }


}
