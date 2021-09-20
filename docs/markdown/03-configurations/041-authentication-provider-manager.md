# La brique authentication provider

<div class="full-center">
    <img src="./assets/images/3_authentication_provider/authentication_provider_manager_1_base.png">
</div>

##==##
# AuthenticationManager

<ul>
    <li class="fragment">Reçoit la requête depuis le filtre...</li>
    <li class="fragment">...puis choisit le provider correspondant</li>
    <li class="fragment">Pattern chaîne de responsabilités</li>
</ul>

##==##
# AuthenticationProvider

<ul>
    <li class="fragment">Indique s'il peut traiter ou non une requête</li>
    <li class="fragment">Logique d'authentification</li>
    <li class="fragment">Par défaut -> login/password</li>
</ul>

##==##
# Choisir une manière de s'authentifier
<div class="full-center">
    <img src="./assets/images/3_authentication_provider/multiple_authentications_1.png">
</div>

##==##
# Choisir une manière de s'authentifier
<div class="full-center">
    <img src="./assets/images/3_authentication_provider/multiple_authentications_2.png">
</div>

##==##
# Choisir une manière de s'authentifier
<div class="full-center">
    <img src="./assets/images/3_authentication_provider/multiple_authentications_3.png">
</div>

##==##
# Choisir une manière de s'authentifier
<div class="full-center">
    <img src="./assets/images/3_authentication_provider/multiple_authentications_4.png">
</div>

##==##
# Choisir une manière de s'authentifier
<div class="full-center">
    <img src="./assets/images/3_authentication_provider/multiple_authentications_5.png">
</div>

##==##
# Choisir une manière de s'authentifier
<div class="full-center">
    <img src="./assets/images/3_authentication_provider/multiple_authentications_6.png">
</div>

##==##
# Choisir une manière de s'authentifier
<div class="full-center">
    <img src="./assets/images/3_authentication_provider/multiple_authentications_7.png">
</div>

##==##
# Choisir une manière de s'authentifier
<div class="full-center">
    <img src="./assets/images/3_authentication_provider/multiple_authentications_8.png">
</div>

##==##
# Choisir une manière de s'authentifier
<div class="full-center">
    <img src="./assets/images/3_authentication_provider/multiple_authentications_9.png">
</div>

##==##
# L'évènement d'authentification

##==##
# L'évènement d'authentification
<br>

## Principal
```java
public interface Principal {
    String getName();
}
```

##==##
# L'évènement d'authentification
<br>

## Principal
```java
public interface Principal {
    String getName();
}
```

<br>

## Authentication
```java

public interface Authentication extends Principal, Serializable {
}
```

##==##
# L'évènement d'authentification
<br>

## Principal
```java
public interface Principal {
    String getName();
}
```

<br>

## Authentication
```java

public interface Authentication extends Principal, Serializable {
  Object getCredentials();
}
```
##==##
# L'évènement d'authentification
<br>

## Principal
```java
public interface Principal {
    String getName();
}
```

<br>

## Authentication
```java

public interface Authentication extends Principal, Serializable {
  Object getCredentials();
  Collection<? extends GrantedAuthority> getAuthorities();
}
```
##==##
# L'évènement d'authentification
<br>

## Principal
```java
public interface Principal {
    String getName();
}
```

<br>

## Authentication
```java

public interface Authentication extends Principal, Serializable {
  Object getCredentials();
  Collection<? extends GrantedAuthority> getAuthorities();
  Object getPrincipal();
}
```
##==##
# L'évènement d'authentification
<br>

## Principal
```java
public interface Principal {
    String getName();
}
```

<br>

## Authentication
```java

public interface Authentication extends Principal, Serializable {
  Object getCredentials();
  Collection<? extends GrantedAuthority> getAuthorities();
  Object getPrincipal(); 
  boolean isAuthenticated();
}
```
##==##
# L'évènement d'authentification
<br>

## Principal
```java
public interface Principal {
    String getName();
}
```

<br>

## Authentication
```java

public interface Authentication extends Principal, Serializable {
  Object getCredentials();
  Collection<? extends GrantedAuthority> getAuthorities();
  Object getPrincipal(); 
  boolean isAuthenticated();
  void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException;
}
```
##==##
# L'évènement d'authentification
<br>

## Principal
```java
public interface Principal {
    String getName();
}
```

<br>

## Authentication
```java

public interface Authentication extends Principal, Serializable {
  Object getCredentials();
  Collection<? extends GrantedAuthority> getAuthorities();
  Object getPrincipal(); 
  boolean isAuthenticated();
  void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException;
  Object getDetails();
}
```

##==##
# L'interface AuthenticationProvider

```java
public interface AuthenticationProvider {
}
```

##==##
# L'interface AuthenticationProvider

```java
public interface AuthenticationProvider {
  Authentication authenticate(Authentication authentication) throws AuthenticationException;
}
```

##==##
# L'interface AuthenticationProvider

```java
public interface AuthenticationProvider {
  Authentication authenticate(Authentication authentication) throws AuthenticationException;
  boolean supports(Class<?> authentication);
}
```

##==##
# Choisir une manière de s'authentifier
<div class="full-center">
    <img src="./assets/images/3_authentication_provider/multiple_authentications_completed_1.png">
</div>

##==##
# Choisir une manière de s'authentifier
<div class="full-center">
    <img src="./assets/images/3_authentication_provider/multiple_authentications_completed_2.png">
</div>

##==##
# Choisir une manière de s'authentifier
<div class="full-center">
    <img src="./assets/images/3_authentication_provider/multiple_authentications_completed_3.png">
</div>

##==##
# Choisir une manière de s'authentifier
<div class="full-center">
    <img src="./assets/images/3_authentication_provider/multiple_authentications_completed_4.png">
</div>

##==##
# Choisir une manière de s'authentifier
<div class="full-center">
    <img src="./assets/images/3_authentication_provider/multiple_authentications_completed_5.png">
</div>

##==##
# Choisir une manière de s'authentifier
<div class="full-center">
    <img src="./assets/images/3_authentication_provider/multiple_authentications_completed_6.png">
</div>

##==##
# Choisir une manière de s'authentifier
<div class="full-center">
    <img src="./assets/images/3_authentication_provider/multiple_authentications_completed_7.png">
</div>

##==##
# Choisir une manière de s'authentifier
<div class="full-center">
    <img src="./assets/images/3_authentication_provider/multiple_authentications_completed_8.png">
</div>

##==##
# Choisir une manière de s'authentifier
<div class="full-center">
    <img src="./assets/images/3_authentication_provider/multiple_authentications_completed_9.png">
</div>


##==##
# Créer un provider custom
```java
public class CustomAuthenticationProvider {
}
```

##==##
# Créer un provider custom
```java
@Component
public class CustomAuthenticationProvider {
}
```

##==##
# Créer un provider custom
```java
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
}
```

##==##
# Créer un provider custom
```java
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    }
}
```

##==##
# Créer un provider custom
```java
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
    }
}
```

##==##
# Créer un provider custom
```java
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
    }
}
```

##==##
# Créer un provider custom
```java
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        AdditionalDetails additionalDetails = (AdditionalDetails) authentication.getDetails();
    }
}
```

##==##
# Créer un provider custom
```java
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        AdditionalDetails additionalDetails = (AdditionalDetails) authentication.getDetails();
        
        if (authenticationReliesOnAThirdPartySystem()) {
        }
    }
}
```

##==##
# Créer un provider custom
```java
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        AdditionalDetails additionalDetails = (AdditionalDetails) authentication.getDetails();
        
        if (authenticationReliesOnAThirdPartySystem()) {
            if (thirdPartyApprovesAuthentication(name, password, additionalDetails)) {
            }
        }
    }
}
```

##==##
# Créer un provider custom
```java
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        AdditionalDetails additionalDetails = (AdditionalDetails) authentication.getDetails();
        
        if (authenticationReliesOnAThirdPartySystem()) {
            if (thirdPartyApprovesAuthentication(name, password, additionalDetails)) {
                return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
            }
        }
    }
}
```

##==##
# Créer un provider custom
```java
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        AdditionalDetails additionalDetails = (AdditionalDetails) authentication.getDetails();
        
        if (authenticationReliesOnAThirdPartySystem()) {
            if (thirdPartyApprovesAuthentication(name, password, additionalDetails)) {
                return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
            } else {
                throw new BadCredentialsException("Authentication failed!");
            }
        }
    }
}
```

##==##
# Créer un provider custom
```java
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        AdditionalDetails additionalDetails = (AdditionalDetails) authentication.getDetails();
        
        if (authenticationReliesOnAThirdPartySystem()) {
            if (thirdPartyApprovesAuthentication(name, password, additionalDetails)) {
                return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
            } else {
                throw new BadCredentialsException("Authentication failed!");
            }
        } else {
            return null;
        }
    }
}
```

##==##
# Créer un provider custom
```java
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        AdditionalDetails additionalDetails = (AdditionalDetails) authentication.getDetails();
        
        if (authenticationReliesOnAThirdPartySystem()) {
            if (thirdPartyApprovesAuthentication(name, password, additionalDetails)) {
                return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
            } else {
                throw new BadCredentialsException("Authentication failed!");
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
    }
}
```

##==##
# Créer un provider custom
```java
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        AdditionalDetails additionalDetails = (AdditionalDetails) authentication.getDetails();
        
        if (authenticationReliesOnAThirdPartySystem()) {
            if (thirdPartyApprovesAuthentication(name, password, additionalDetails)) {
                return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
            } else {
                throw new BadCredentialsException("Authentication failed!");
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
```

##==##
# Créer un provider custom: refactorons!
```java
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        AdditionalDetails additionalDetails = (AdditionalDetails) authentication.getDetails();
        
        if (!authenticationReliesOnAThirdPartySystem()) {
            return null;
        }

        if (thirdPartyApprovesAuthentication(name, password, additionalDetails)) {
            return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
        }
        throw new BadCredentialsException("Authentication failed!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
```

##==##
# Ajouter le provider
```java
@Configuration
public class SchoolSecurityConfigurer extends WebSecurityConfigurerAdapter {
    
  @Autowired
  private CustomAuthenticationProvider customAuthenticationProvider;
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(customAuthenticationProvider);
  }
  // ...
}
```

##==##
# Ajouter plusieurs providers
```java
@Configuration
public class SchoolSecurityConfigurer extends WebSecurityConfigurerAdapter {
  
  //...
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
  }
  // ...
}
```

##==##
# Ajouter plusieurs providers
```java
@Configuration
public class SchoolSecurityConfigurer extends WebSecurityConfigurerAdapter {
    
  @Autowired
  private AuthenticationProvider1 provider1;
  
  //...
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(provider1);
  }
  // ...
}
```

##==##
# Ajouter plusieurs providers
```java
@Configuration
public class SchoolSecurityConfigurer extends WebSecurityConfigurerAdapter {
    
  @Autowired
  private AuthenticationProvider1 provider1;

  @Autowired
  private AuthenticationProvider2 provider2;
  
  //...
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(provider1)
        .authenticationProvider(provider2);
  }
  // ...
}
```

##==##
# Ajouter plusieurs providers
```java
@Configuration
public class SchoolSecurityConfigurer extends WebSecurityConfigurerAdapter {
    
  @Autowired
  private AuthenticationProvider1 provider1;

  @Autowired
  private AuthenticationProvider2 provider2;

  @Autowired
  private AuthenticationProvider3 provider3;
  
  //...
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(provider1)
        .authenticationProvider(provider2)
        .authenticationProvider(provider3);
  }
  // ...
}
```

##==##
# Quelques classes qui implémentent AuthenticationProvider
<ul>
    <li class="fragment">DaoAuthenticationProvider</li>
    <li class="fragment">OAuth2LoginAuthenticationProvider</li>
    <li class="fragment">LdapAuthenticationProvider</li>
</ul>

##==##
# Quelques classes qui implémentent AuthenticationProvider
<ul>
    <li>DaoAuthenticationProvider</li>
    <li>OAuth2LoginAuthenticationProvider</li>
    <li>LdapAuthenticationProvider</li>
</ul>

Javadoc: https://docs.spring.io/spring-security/site/docs/4.0.x/apidocs/org/springframework/security/authentication/AuthenticationProvider.html
