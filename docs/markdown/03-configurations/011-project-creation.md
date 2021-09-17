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
    <li class="fragment">Sécuriser notre application</li>
    <li class="fragment">Ajouter un formulaire de login - se logguer, délogguer</li>
    <li class="fragment">Afficher le nom de l'utilisateur connecté</li>
    <li class="fragment">Configurer nos utilisateurs</li>
    <li class="fragment">Sécuriser nos mots de passe</li>
</ol>

##==##
# Démo: configurons un peu Spring Security
## Sécuriser notre application

##==##
# Démo: configurons un peu Spring Security
## Ajouter un formulaire de login - se logguer, délogguer

##==##
# Démo: configurons un peu Spring Security
## Afficher le nom de l'utilisateur connecté

##==##
<!-- .slide: class="exercice" -->
# Récupérer l'utilisateur connecté
## Exercice 1
<br>
En plus du nom de l'utilisateur, afficher son/ses role(s)

##==##
# Démo: configurons un peu Spring Security
## Configurer nos utilisateurs

##==##
# Démo: configurons un peu Spring Security
## Sécuriser nos mots de passe

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
Dans la classe SchoolSecurityConfigurer de l'exercice 2, faire chacun des TODO

```java
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .denyAll();
        // TODO 1. l'url /public est accessible par tout le monde (authentifié ou non)
        // TODO 2. l'url /anonyme est accessible par tout le monde, à condition d'être authentifié
        // TODO 3. l'url /private n'est accessible que par les utilisateurs authentifiés et ayant un rôle ADMIN ou SFEIR
        // TODO 4. l'url /forbidden et /access-denied ne sont accessibles par personne
        // TODO 5. les urls commencant par /admin sont accessibles seulement par les utilisateurs ayant le rôle ADMIN...
        // TODO 6. ... sauf si l'url contient forbidden, dans ce cas elle est inaccessible (ex: admin/a/forbidden/b/c)
        // TODO 6. les urls commencant par /sfeir sont accessible par les utilisateurs ayant un rôle ADMIN ou SFEIR...
        // TODO 7. ... sauf si elles commencent par /sfeir/special: elles sont accessibles seulement par les utilisateur SFEIR mais pas les utilisateurs ADMIN
        // TODO 8. les urls contenant les codes us, au, ca ou uk sont visibles par tout le monde (par exemple /resource/us/view ou /uk/a/b/c)
        // TODO 9. les resources dont:
        // TODO    - l'url commence par /resource/sensitive,
        // TODO    - le chemin a pour taille 3 (exemple: s'applique à /resource/sensitive/exemple mais pas à /resource/sensitive ou à /resource/sensitive/test1/test2)
        // TODO    - la methode HTTP est POST
        // TODO ne sont pas accessibles
        // TODO 10. Toute les autres requêtes sont accessibles pour les personnes authentifiés
    }
```
<br>
Conseil: faite attention à l'ordre de vos filtres ;)
