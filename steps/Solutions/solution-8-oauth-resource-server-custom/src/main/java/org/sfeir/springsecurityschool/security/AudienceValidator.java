package org.sfeir.springsecurityschool.security;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.Assert;

import java.util.List;

public class AudienceValidator implements OAuth2TokenValidator<Jwt> {

  private final String audience;

  private OAuth2Error error;

  /**
   * Instantiates a new Audience validator.
   *
   * @param acceptedAudience the accepted audience
   */
  public AudienceValidator(String acceptedAudience) {
    this.audience = acceptedAudience;
    this.error = new OAuth2Error("invalid_token", "The required audience " + audience + " is missing", null);
  }

  /**
   * Validate that the required audience is specified in the token
   */
  @Override
  public OAuth2TokenValidatorResult validate(Jwt jwt) {
    if (jwt.getAudience().contains(audience)) {
      return OAuth2TokenValidatorResult.success();
    } else {
      return OAuth2TokenValidatorResult.failure(error);
    }
  }
}
