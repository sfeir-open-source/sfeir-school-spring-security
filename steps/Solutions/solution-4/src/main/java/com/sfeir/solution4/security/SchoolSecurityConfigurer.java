package com.sfeir.solution4.security;

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
        Map<String, PasswordEncoder> encoders = Map.ofEntries(
                entry("noop", NoOpPasswordEncoder.getInstance()),
                entry("bcrypt", new BCryptPasswordEncoder()),
                entry("pbkdf2", new Pbkdf2PasswordEncoder("secret", 10000, 128))
        );
        return new DelegatingPasswordEncoder("pbkdf2", encoders);
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
                .authorizeRequests()
                .antMatchers("/private").authenticated()
                .antMatchers("/public", "/h2-console/**", "/register").permitAll()
                .antMatchers("/password/update").hasAuthority("ADMIN")
                .anyRequest().authenticated();
    }
}
