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

    private final RequestUnuuidifierFilter requestUnuuidifierFilter;

    public SecurityConfig(RequestUnuuidifierFilter requestUnuuidifierFilter) {
        this.requestUnuuidifierFilter = requestUnuuidifierFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().disable().csrf().disable().addFilterAfter(requestUnuuidifierFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest()
                .permitAll();
    }
}
