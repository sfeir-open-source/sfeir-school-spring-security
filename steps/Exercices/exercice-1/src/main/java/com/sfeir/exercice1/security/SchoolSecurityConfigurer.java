package com.sfeir.exercice1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SchoolSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder encoder() {
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
