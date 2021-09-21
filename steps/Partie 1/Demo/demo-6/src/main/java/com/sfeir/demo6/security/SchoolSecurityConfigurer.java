package com.sfeir.demo6.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SchoolSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JdbcUserDetailsManager userDetailsManager() throws Exception {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()
                .httpBasic() // permet l'authentification
                .and()
                .formLogin()
                .and()
                .oauth2Login()
                .and()
                .authorizeRequests()
                .antMatchers("/private").authenticated()
                .antMatchers("/public", "/h2-console/**", "/register").permitAll()
                .anyRequest().authenticated();
    }
}
