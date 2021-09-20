# Comment créer un nouveau projet ?
## Pour démarrer un projet Spring Security
<ol>
    <li class="fragment">Avoir un projet Spring</li>
    <li class="fragment">Ajouter les dépendances Spring Security</li>
    <li class="fragment">Ajouter une classe de configuration</li>
</ol>

##==##
# Démo: ajoutons Spring Security à notre projet!
## Les dépendances Maven
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
##==##
# Démo: ajoutons Spring Security à notre projet!
## Ajoutons la classe de configuration
```java
@EnableWebSecurity
public class SchoolSecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO
	}
    
}
```

##==##
# Et si on a pas de projet Spring sous la main ?
Pas de soucis...

##==##
# Et si on a pas de projet Spring sous la main ?
Pas de soucis...
On peut utiliser Spring Initialzr!

##==##
# Et si on a pas de projet Spring sous la main ?
Pas de soucis...
On peut utiliser Spring Initialzr!

Où le trouver ?
* url: https://start.spring.io
* plugin dans votre IDE favoris

##==##
# Démo: configurons un peu Spring Security
## Dans cette démo on va:

<ol>
    <li class="fragment">Ajouter Spring Security</li>
    <li class="fragment">Ajouter un formulaire de login - se logguer, délogguer</li>
    <li class="fragment">Afficher le nom de l'utilisateur connecté</li>
    <li class="fragment">Configurer nos utilisateurs</li>
    <li class="fragment">Se connecter via OAuth2</li>
    <li class="fragment">Sécuriser nos mots de passe</li>
</ol>

##==##
# Démo: configurons un peu Spring Security
## Ajouter Spring Security

##==##
# Démo: configurons un peu Spring Security
## Ajouter Spring Security
### Takeaway

<ol>
  <li class="fragment">Comportement par défaut</li>
  <ol>
    <li class="fragment">Resources sécurisées</li>
    <li class="fragment">Utilisateur avec mot de passe généré</li>
  </ol>
  <li class="fragment">Configuration</li>
  <ol>
    <li class="fragment">WebSecurityConfigurerAdapter</li>
    <li class="fragment">configure(HttpSecurity http)</li>
    <li class="fragment">http.httpBasic()</li>
    <li class="fragment">http.authorizeRequest()</li>
  </ol>
</ol>

##==##
# Démo: configurons un peu Spring Security
## Ajouter un formulaire de login - se logguer, délogguer

##==##
# Démo: configurons un peu Spring Security
## Ajouter un formulaire de login - se logguer, délogguer
### Takeaway
<ol>
  <li class="fragment">http.formLogin()</li>
  <li class="fragment">se délogguer via /logout</li>
</ol>

##==##
# Démo: configurons un peu Spring Security
## Afficher le nom de l'utilisateur connecté

##==##
# Démo: configurons un peu Spring Security
## Afficher le nom de l'utilisateur connecté
### Takeaway
<ol>
  <li class="fragment">Injection dans le controller</li>
  <li class="fragment">Principal, Authentication ou UsernamePasswordAuthenticationToken</li>
</ol>

##==##
<!-- .slide: class="exercice" -->
# Récupérer l'utilisateur connecté
## Exercice 1
<br>
En plus du nom de l'utilisateur, afficher son/ses role(s)

##==##
# Démo: configurons un peu Spring Security
## Configurer nos utilisateurs
### Prérequis

##==##
# Démo: configurons un peu Spring Security
## Configurer nos utilisateurs
### Prérequis: configurer h2
```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driverClassName: org.h2.Driver
    username: sfeir
    password: school
  jpa:
    defer-datasource-initialization: true
    database-platform: org.hibernate.dialect.H2Dialect
  h2:
    console:
      enabled: true
```

##==##
# Démo: configurons un peu Spring Security
## Configurer nos utilisateurs
### Prérequis: ajouter quelques utilisateurs
```sql
INSERT INTO SCHOOL_USER (name, role, password, enabled) VALUES
('admin', 'ADMIN', 'admin', true),
('tutor', 'SFEIR', 'sfeir', true),
('student', 'VISITOR', 'student', true),
('disabled_user', 'VISITOR', 'abc', false);
```

##==##
# Démo: configurons un peu Spring Security
## Configurer nos utilisateurs
### Prérequis: voir la console h2
```java
http
  .csrf()
  .disable()
  .headers()
  .frameOptions()
  .sameOrigin()
  // ...
  .antMatchers("/h2-console/**").permitAll()
  // ...
```

##==##
# Démo: configurons un peu Spring Security
## Configurer nos utilisateurs
### Takeaway
<ol>
  <li class="fragment">Lier avec les utilisateurs en base de données</li>
  <ol>
    <li class="fragment">configure(AuthenticationManagerBuilder auth)</li>
    <li class="fragment">JdbcUserDetailsManager</li>
  </ol>
  <li class="fragment">Comptes utilisateurs désactivés</li>
  <li class="fragment">Plus d'utilisateur généré!</li>
</ol>

##==##
# Démo: configurons un peu Spring Security
## Se connecter via OAuth2
### Prérequis

##==##
# Démo: configurons un peu Spring Security
## Se connecter via OAuth2
### Prérequis: créer un clientId/secret
```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          github:
            clientId: <client-id>
            clientSecret: <client-secret>
```

##==##
# Démo: configurons un peu Spring Security
## Se connecter via OAuth2
### Takeaway
<ol>
  <li class="fragment">spring-boot-starter-oauth2-client</li>
  <li class="fragment">client-id et client-secret</li>
  <li class="fragment">oauth2Login()</li>
  <li class="fragment">OAuth2AuthenticationToken et OAuth2User</li>
</ol>

##==##
# Démo: configurons un peu Spring Security
## Sécuriser nos mots de passe
### Prérequis

##==##
# Démo: configurons un peu Spring Security
## Sécuriser nos mots de passe
### Prérequis: changer le schéma de la base
```sql
CREATE TABLE IF NOT EXISTS `users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(250) NOT NULL,
  `enabled` INT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `authorities` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `authority` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
```

##==##
# Démo: configurons un peu Spring Security
## Sécuriser nos mots de passe
### Takeaway
<ol>
  <li class="fragment">PasswordEncoder</li>
  <ol>
    <li class="fragment">Bean PasswordEncoder</li>
    <li class="fragment">encoder.encode()</li>
  </ol>
  <li class="fragment">UserDetailsManager</li>
  <ol>
    <li class="fragment">JdbcUserDetailsManager: moins de code pour gérer les utilisateurs</li>
    <li class="fragment">manager.createUser(new User(...))</li>
  </ol>
</ol>

##==##
# Définition: Authentification et Authorisation

##==##
# Définition: Authentification
<div class="full-center">
    <img src="./assets/images/0_creation_project/authentication_authorization_1.png">
</div>

##==##
# Définition: Authentification
<div class="full-center">
    <img src="./assets/images/0_creation_project/authentication_authorization_2.png">
</div>

##==##
# Définition: Authentification
<div class="full-center">
    <img src="./assets/images/0_creation_project/authentication_authorization_3.png">
</div>

##==##
# Définition: Autorisations
<div class="full-center">
    <img src="./assets/images/0_creation_project/authentication_authorization_4.png">
</div>

##==##
# Définition: Autorisations
<div class="full-center">
    <img src="./assets/images/0_creation_project/authentication_authorization_5.png">
</div>

##==##
# Définition: Autorisations
<div class="full-center">
    <img src="./assets/images/0_creation_project/authentication_authorization_6.png">
</div>

##==##
# Autoriser ou refuser une requête
```java
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests();
    }
```

##==##
# Autoriser ou refuser une requête
## 1. Sélectionner une ou des resources

##==##
# Autoriser ou refuser une requête
## 1. Sélectionner n'importe quelle resource
```java
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest();
    }
```

##==##
# Autoriser ou refuser une requête
## 1. Sélectionner une resource précise
```java
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/resource");
    }
```

##==##
# Autoriser ou refuser une requête
## 1. Sélectionner un ensemble de resources
```java
    @Override 
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/resource/*/a");
}
```

##==##

# Autoriser ou refuser une requête
## 1. Sélectionner un ensemble de resources
```java
    @Override 
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/resource/**/a");
}
```

##==##

# Autoriser ou refuser une requête
## 1. Sélectionner une ou des resources liée à une méthode HTTP donnée
```java
    @Override 
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.PUT, "/resource/**/a");
}
```

##==##
# Autoriser ou refuser une requête
## 1. Sélectionner un ensemble de resources avec une regex
```java
    @Override 
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .regexMatchers("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01- \x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e- \x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9- ]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0- 5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01- \x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e- \x7f])+)\])");
}
```

##==##
# Autoriser ou refuser une requête
## 2. Appliquer une restriction

##==##
# Autoriser ou refuser une requête
## 2. Appliquer une restriction: accepter tout le monde
```java
    @Override 
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/resource/**")
            .permitAll();
}
```

##==##
# Autoriser ou refuser une requête
## 2. Appliquer une restriction: refuser tout le monde
```java
    @Override 
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/resource/**")
            .denyAll();
}
```

##==##
# Autoriser ou refuser une requête
## 2. Appliquer une restriction: accepter les utilisateurs authentifiées
```java
    @Override 
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/resource/**")
            .authenticated();
}
```

##==##
# Autoriser ou refuser une requête
## 2. Appliquer une restriction: accepter les utilisateurs ayant un certain rôle 
```java
    @Override 
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/resource/**")
            .hasRole("READ");
}
```

##==##
# Autoriser ou refuser une requête
## 2. Appliquer une restriction: accepter les utilisateurs ayant certains rôles
```java
    @Override 
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/resource/**")
            .hasAnyRole("READ", "WRITE");
}
```

##==##
# Autoriser ou refuser une requête
## 2. Appliquer une restriction: appliquer des règles
```java
    @Override 
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/resource/**")
            .access("hasRole('READ') and !hasRole('WRITE')");
}
```

##==##
# Autoriser ou refuser une requête
## 3. Enchaîner les restrictions
```java
    @Override 
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests();
}
```

##==##
# Autoriser ou refuser une requête
## 3. Enchaîner les restrictions
```java
    @Override 
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/resource1/**").permitAll();
}
```

##==##
# Autoriser ou refuser une requête
## 3. Enchaîner les restrictions
```java
    @Override 
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/resource1/**").permitAll()
            .antMatchers("/resource2/**").denyAll();
}
```

##==##
# Autoriser ou refuser une requête
## 3. Enchaîner les restrictions
```java
    @Override 
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/resource1/**").permitAll()
            .antMatchers("/resource2/**").denyAll()
            .anyRequest().authenticated();
}
```

##==##
# Autoriser ou refuser une requête
## 3. Enchaîner les restrictions
```java
    @Override 
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/resource1/**").permitAll()
            .antMatchers("/resource2/**").denyAll()
            .anyRequest().authenticated();
}
```

## Faire attention à l'ordre!

##==##
<!-- .slide: class="exercice" -->
# Restreindre l'accès à des ressources
## Exercice 2
<br>
Ajouter l'ensemble des restrictions demandées, que vous trouverez dans le README de l'exercice 2.
