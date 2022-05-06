package com.sfeir.solution2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SchoolSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                // 1. l'url /public est accessible par tout le monde (authentifié ou non)
                .antMatchers("/public").permitAll()
                // 2. l'url /anonyme est accessible par tout le monde, à condition d'être authentifié
                .antMatchers("/anonyme").authenticated()
                // 3. l'url /private n'est accessible que par les utilisateurs authentifiés et ayant un rôle ADMIN ou SFEIR
                .antMatchers("/private").hasAnyRole("ADMIN", "SFEIR")
                // 4. l'url /forbidden et /access-denied ne sont accessibles par personne
                .antMatchers("/forbidden", "/access-denied").denyAll()
                // 5.b. ... sauf si l'url contient forbidden, dans ce cas elle est inaccessible (ex: admin/a/forbidden/b/c)
                .antMatchers("/admin/**/forbidden/**").denyAll()
                // 5.a. les urls commencant par /admin sont accessibles seulement par les utilisateurs ayant le rôle ADMIN...
                .antMatchers("/admin/**").hasRole("ADMIN")
                // 6.b. ... sauf si elles commencent par /sfeir/special: elles sont accessibles seulement par les utilisateurs SFEIR mais pas les utilisateurs ADMIN
                .antMatchers("/sfeir/special/**").access("hasRole('SFEIR') and !hasRole('ADMIN')")
                // 6.a. les urls commencant par /sfeir sont accessibles par les utilisateurs ayant un rôle ADMIN ou SFEIR...
                .antMatchers("/sfeir/**").hasAnyRole("ADMIN", "SFEIR")
                // 7. les urls contenant les codes us, au, ca ou uk sont visibles par tout le monde (par exemple /resource/us/view ou /uk/a/b/c)
                .regexMatchers(".*/(us|au|ca|uk)/.*").permitAll()
                // 8. les resources dont:
                //    - l'url commence par /resource/sensitive,
                //    - le chemin a pour taille 3 (exemple: s'applique à /resource/sensitive/exemple mais pas à /resource/sensitive ou à /resource/sensitive/test1/test2)
                //    - la methode HTTP est POST
                // ne sont pas accessibles
                .antMatchers(HttpMethod.POST, "/resource/sensitive/*").denyAll()
                // 9. Toutes les autres requêtes sont accessibles pour les personnes authentifiées
                .anyRequest().authenticated();
    }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // Create user in memory
    auth.inMemoryAuthentication()
      .withUser("admin")
      .password("admin")
      .roles("ADMIN");

    auth.inMemoryAuthentication()
      .withUser("tutor")
      .password("sfeir")
      .roles("SFEIR");

    auth.inMemoryAuthentication()
      .withUser("student")
      .password("student")
      .roles("VISITOR");

    auth.inMemoryAuthentication()
      .withUser("disabled_user")
      .password("abc")
      .roles("VISITOR");
  }
}
