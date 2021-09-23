package com.sfeir.exercice4.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.sql.DataSource;
import java.util.Map;

import static java.util.Map.entry;

@Configuration
public class SchoolSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder encoder() {
        // TODO 1.
        // Indice: utiliser DelegatingPasswordEncoder pour gérer plusieurs algorithmes
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select name, password, enabled from school_user where name = ?")
                .authoritiesByUsernameQuery("select name, role from school_user where name = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO 3. seul les ADMIN peuvent mettre à jour les mots de passe
        http
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .and()
                .authorizeRequests()
                .antMatchers("/private").authenticated()
                .antMatchers("/public", "/h2-console/**", "/register").permitAll()
                .anyRequest().authenticated();
    }
}
