package org.sfeir.springsecurityschool.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

@Component
public class GlobalSecurity extends WebSecurityConfigurerAdapter {

  @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
  private String issuer;

  @Value("${auth0.audience}")
  private String audience;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .antMatchers("/public").permitAll()
      .antMatchers("/secure").authenticated()
      .antMatchers("/super-secure").hasAuthority("SCOPE_admin")
      .and()
      .oauth2ResourceServer().jwt().decoder(customJwtDecoder());
  }

  private JwtDecoder customJwtDecoder() {
    OAuth2TokenValidator<Jwt> withAudience = new AudienceValidator(audience);
    OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
    OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(withAudience, withIssuer);

    NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer);
    jwtDecoder.setJwtValidator(validator);
    return jwtDecoder;
  }
}
