# La brique de mots de passe

<div class="full-center">
    <img src="./assets/images/2_password_encoder/password_encoder_1_base.png">
</div>

##==##
# A quoi sert un passwordEncoder ?

<div class="full-center">
    <img src="./assets/images/2_password_encoder/meat-grinder.gif">
</div>

##==##
# Un scénario sans encoder
<div class="full-center">
    <img src="./assets/images/2_password_encoder/hachage_1.png">
</div>

##==##
# Un scénario sans encoder
<div class="full-center">
    <img src="./assets/images/2_password_encoder/hachage_2.png">
</div>

##==##
# Un scénario sans encoder
<div class="full-center">
    <img src="./assets/images/2_password_encoder/hachage_3.png">
</div>

##==##
# Un scénario sans encoder
<div class="full-center">
    <img src="./assets/images/2_password_encoder/hachage_4.png">
</div>

##==##
# Un scénario sans encoder
<div class="full-center">
    <img src="./assets/images/2_password_encoder/hachage_5.png">
</div>

##==##
# Principe
<div class="full-center">
    <img src="./assets/images/2_password_encoder/hachage_6.png">
</div>

##==##
# Principe
<div class="full-center">
    <img src="./assets/images/2_password_encoder/hachage_7.png">
</div>

##==##
# Vulnérabilité
<div class="full-center">
    <img src="./assets/images/2_password_encoder/hachage_9.png">
</div>

##==##
# Passe moi le sel!
<div class="full-center">
    <img src="./assets/images/2_password_encoder/salt.gif">
</div>

##==##
# Regardons un peu le code: PasswordEncoder

```java
public interface PasswordEncoder {
    
  String encode(CharSequence rawPassword);
  
  boolean matches(CharSequence rawPassword, String encodedPassword);
  
  default boolean upgradeEncoding(String encodedPassword) {
    return false;
  }
}
```

##==##
# Principe
<div class="full-center">
    <img src="./assets/images/2_password_encoder/hachage_8.png">
</div>

##==##
# Indiquer à Spring comment gérer les mots de passe
3 façons:
<ul>
    <li class="fragment">Ne rien faire</li>
</ul>

##==##
# Indiquer à Spring comment gérer les mots de passe
3 façons:
<ul>
    <li>Ne rien faire</li>
    <li>Ajouter un bean</li>
</ul>

##==##
# Indiquer à Spring comment gérer les mots de passe
3 façons:
<ul>
    <li>Ne rien faire</li>
    <li>Ajouter un bean</li>
</ul>

```java
@EnableWebSecurity
@Configuration
public class SchoolSecurityConfigurer extends WebSecurityConfigurerAdapter {
    
  // ...

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  // ...
}
```

##==##
# Indiquer à Spring comment gérer les mots de passe
3 façons:
<ul>
    <li>Ne rien faire</li>
    <li>Ajouter un bean</li>
    <li>L'ajouter à l'AuthenticationManagerBuilder</li>
</ul>

##==##
# Indiquer à Spring comment gérer les mots de passe
3 façons:
<ul>
    <li>Ne rien faire</li>
    <li>Ajouter un bean</li>
    <li>L'ajouter à l'AuthenticationManagerBuilder</li>
</ul>

```java
@EnableWebSecurity
@Configuration
public class SchoolSecurityConfigurer extends WebSecurityConfigurerAdapter {

  @Autowired
  private DataSource dataSource;
  
  // ...

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication()
      .dataSource(dataSource)
      .passwordEncoder(new BCryptPasswordEncoder());
  }
}
```

##==##
# Ce que fournit Spring Security

<ul>
    <li class="fragment">NoOpPasswordEncoder</li>
    <li class="fragment">BCryptPassword</li>
    <li class="fragment">SCryptPassword</li>
    <li class="fragment">et bien d'autres!</li>
</ul>

##==##
# Ce que fournit Spring Security

<ul>
    <li>NoOpPasswordEncoder</li>
    <li>BCryptPassword</li>
    <li>SCryptPassword</li>
    <li>et bien d'autres!</li>
</ul>

javadoc: https://docs.spring.io/spring-security/site/docs/4.2.4.RELEASE/apidocs/org/springframework/security/crypto/password/PasswordEncoder.html

##==##
# Gérer plusieurs PasswordEncoder: scénario

<div class="full-center">
    <img src="./assets/images/2_password_encoder/hachage_10.png">
</div>

##==##
# Gérer plusieurs PasswordEncoder: scénario

<div class="full-center">
    <img src="./assets/images/2_password_encoder/hachage_11.png">
</div>

##==##
# Gérer plusieurs PasswordEncoder: scénario

<div class="full-center">
    <img src="./assets/images/2_password_encoder/hachage_12.png">
</div>

##==##
# Gérer plusieurs PasswordEncoder: DelegatingPasswordEncoder

```java
@EnableWebSecurity
@Configuration
public class SchoolSecurityConfigurer extends WebSecurityConfigurerAdapter {
    
  // ...
  
  @Bean
  public PasswordEncoder passwordEncoder() {
  }
}
```

##==##
# Gérer plusieurs PasswordEncoder: DelegatingPasswordEncoder

```java
@EnableWebSecurity
@Configuration
public class SchoolSecurityConfigurer extends WebSecurityConfigurerAdapter {
    
  // ...
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    Map<String, PasswordEncoder> encoders = new HashMap<>();
  }
}
```

##==##
# Gérer plusieurs PasswordEncoder: DelegatingPasswordEncoder

```java
@EnableWebSecurity
@Configuration
public class SchoolSecurityConfigurer extends WebSecurityConfigurerAdapter {
    
  // ...
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    Map<String, PasswordEncoder> encoders = new HashMap<>();
    encoders.put("noop", NoOpPasswordEncoder.getInstance());
  }
}
```

##==##
# Gérer plusieurs PasswordEncoder: DelegatingPasswordEncoder

```java
@EnableWebSecurity
@Configuration
public class SchoolSecurityConfigurer extends WebSecurityConfigurerAdapter {
    
  // ...
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    Map<String, PasswordEncoder> encoders = new HashMap<>();
    encoders.put("noop", NoOpPasswordEncoder.getInstance());
    encoders.put("bcrypt", new BCryptPasswordEncoder());
  }
}
```

##==##
# Gérer plusieurs PasswordEncoder: DelegatingPasswordEncoder

```java
@EnableWebSecurity
@Configuration
public class SchoolSecurityConfigurer extends WebSecurityConfigurerAdapter {
    
  // ...
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    Map<String, PasswordEncoder> encoders = new HashMap<>();
    encoders.put("noop", NoOpPasswordEncoder.getInstance());
    encoders.put("bcrypt", new BCryptPasswordEncoder());
    encoders.put("scrypt", new SCryptPasswordEncoder());
  }
}
```

##==##
# Gérer plusieurs PasswordEncoder: DelegatingPasswordEncoder

```java
@EnableWebSecurity
@Configuration
public class SchoolSecurityConfigurer extends WebSecurityConfigurerAdapter {
    
  // ...
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    Map<String, PasswordEncoder> encoders = new HashMap<>();
    encoders.put("noop", NoOpPasswordEncoder.getInstance());
    encoders.put("bcrypt", new BCryptPasswordEncoder());
    encoders.put("scrypt", new SCryptPasswordEncoder());
    return new DelegatingPasswordEncoder("bcrypt", encoders);
  }
}
```

##==##
# Gérer plusieurs PasswordEncoder: différencier les algorithmes

<ul>
    <li class="fragment">{noop}motdepasseenclair</li>
    <li class="fragment">{bcrypt}$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG</li>
</ul>

##==##
<!-- .slide: class="exercice" -->
# Mettre à jour les mots de passe
## Exercice 4
<br>
Tout nos mots de passe sont enregistrés en clair dans la base de données.

Après que le responsable sécurité ai été viré, on souhaite maintenant les hacher sans avoir à en avertir les utilisateurs (que penserons t-il s'ils savaient que leurs mot de passes étaient stockés en clair?).

On utilisera Pbkdf2PasswordEncoder avec les paramètres suivant:
- "secret" comme secret
- 10000 itérations
- 128 comme longueur du hash

### Bonus: Utiliser DelegatingPasswordEncoder et ajouter BCryptEncoder et ScryptEncoder, avec Pbkdf2PasswordEncoder par défaut

##==##
<!-- .slide: class="exercice" -->
# Faille de sécurité
## Question
<br>
Quelle faille de sécurité s'est glissée dans l'exercice précédent ?
