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
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
# Les rôles

##==##
# Définitions
Authorization vs Authentification
- definition générale
- exemple (+ schéma ?)

##==##
# Différence entre role, authorization, permission ?

##==##
# Les matchers
- type de matchers (ant, mvc, regex)
- composition:
  - choisir une url (anyrequest, antmatcher, etc.)
  - exemple -> *, **, regex
  - choisir la restriction (permitAll(), denyAll(), authenticated(), hasRole(), access(), etc)
- parler de l'ordre -> allez du + spécifique au + general

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
