# La brique authentication provider

<div class="full-center">
    <img src="./assets/images/3_authentication_provider/authentication_provider_1_base.png">
</div>

##==##
# Définitions

* logique d'authentification -> comment on s'authentifie ?
* authentication provider -> code qui permet de dire si une requête est ok
* authenticationManager -> choisit le bon provider depuis le filter
* custom auth logic -> plusieurs scénarios (différents mode de connexion géré par une appli)
* default authprovider -> login/password and use UserDetailsService to find user and PasswordEncoder to manage password
* chaine de responsabilité (authmanager)

##==##
# Comment ça fonctionne ?
TODO ajouter schémas (exemple clé/ carte)

##==##
# Comment Spring Security represente l'evenement d'authentification
TODO -> schema avec le principal et l'auth qui l'hérite

TODO en note, expliquer la diff entre principal et userdetails

##==##
# Quelques classes qui implementent Authentication
TODO ajouter liste (loginpassword, oauth2, ldap, jwt, etc.)

##==##
# Un peu de code: l'interface AuthenticationProvider
TODO ajouter code snippet

authenticate() method:
* AuthenticationException
* if null returned -> not supported by this authprovider
* if param is a principal fully auth -> auth param isauth -> true
* no password or sensitive data stored

supports() method: 
* returns true if the auth object is supported
* to be supported, must return true and authenticate must not return null

##==##
# Comment ca fonctionne ?
TODO reprendre schéma mais avec supports(), authenticate(), etc.

# Ajouter un authprovider
```java
@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private AuthenticationProvider authenticationProvider;
  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationProvider);
  }
  // ...
}
```

##==##
# Créer un provider custom (exercice ?)

##==## 
# Exercice -> custom authenticationProvider

