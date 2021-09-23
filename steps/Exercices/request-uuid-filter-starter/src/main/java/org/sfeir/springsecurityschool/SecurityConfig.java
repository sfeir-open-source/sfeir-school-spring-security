package org.sfeir.springsecurityschool;

import org.sfeir.springsecurityschool.filter.RequestUnuuidifierFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //TODO ajouter le filter dans la config

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
        .csrf()
        .disable()
        .authorizeRequests()
        .anyRequest()
        .permitAll();
    }
}
