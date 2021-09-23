package com.sfeir.demo3.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SchoolSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic() // permet l'authentification
                .and()
                .formLogin()
                .and()
                .authorizeRequests()
                .antMatchers("/private").authenticated()
                .antMatchers("/public").permitAll()
                .anyRequest().authenticated();
    }
}
