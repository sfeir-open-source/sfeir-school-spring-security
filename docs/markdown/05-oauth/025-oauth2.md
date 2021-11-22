# OAuth2 : un framework d'autorisation

OAuth2 est un framework d'autorisation, et non d'authentification
</br> ==> Ne pas confondre avec OpenID Connect

Source : https://datatracker.ietf.org/doc/html/rfc6749

Une autorisation est définie par les éléments suivants: 
<ul>
    <li class="fragment">un sujet</li>
    <li class="fragment">une audience</li>
    <li class="fragment">un ou plusieurs scopes</li>
    <li class="fragment">une date d'émission</li>
    <li class="fragment">une date d'expiration</li>
    <li class="fragment">une autorité émettrice</li>
</ul>


##==##
<!-- .slide: class="transition underline" -->
# Vocabulaire

##==##

# Resource Owner

L'utilisateur ou application :
- qui possède les ressources que l'on veut sécuriser
- peut donner un consentement pour accéder à ces données
##==##

# Resource Server

Le serveur qui possède la donnée à laquelle on veut accéder

##==##

# Authorization Server

Le serveur qui autorise un client à accéder à un jeu de ressources donnée
<br/>
Il émettra pour ce faire un token d'autorisation .

##==##

# Client

Le client est le système qui va demander l'accès aux ressources

##==##

# Récapitulatif

<div class="full-center">
    <img src="assets/images/7-oauth2/oauth2-parts.png">
</div>

##==##


# Scopes

Dans le jargon OAuth2, un scope = un droit/role.
Un serveur d'autorisation délivre les scopes autorisé :
- par le ressource owner 
- par sa propre configuration
##==##

# Jwt

JWT = Json Web Tokens

Les jwt sont une manière de representer de la donnée en json sous un format compact et url safe.

Cf RFC rfc7519 ==> https://datatracker.ietf.org/doc/html/rfc7519

##==##

# Jwt encodé

<div class="full-center">
    <img src="./assets/images/7-oauth2/jwt-encoded.png">
</div>
##==##

# Jwt décodé

<div class="full-center">
    <img src="./assets/images/7-oauth2/jwt-decoded.png">
</div>

##==##

# Jwt
<ul>
<li>
Les 3 parties(header, payload,signature) sont encodés via base64-urlsafe.</li>

<li>
Un jwt peut aussi être signé et/ou chiffré.</li>
</ul>

##==##

# Jwt Signé
En général, les Jwt sont signé par une clé RSA(clé publique/privé).
<br/>
On trouvera un champ kid dans le header du token déterminant la clé utilisé pour la signature.

Un serveur d'autorisation expose ses clés via un format appelé JWK
<br/>
Le serveur de ressource récupére ces clés pour tenter de valider le token

##==##


# OAuth2 flow

<div class="full-center">
    <img src="./assets/images/7-oauth2/oauth-flow2.png">
</div>

##==##

# Ajouter le support OAuth2 dans Spring Security 1
Dans le pom.xml ( ou autre gestion de dépendance)
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
</dependency>
```
##==##

# Ajouter le support OAuth2 dans Spring Security 2
Dans le application. yml ( ou application.properties)
```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: <server_uri>
```
##==##

# Ajouter le support OAuth2 dans Spring Security 3
Dans la config applicative
```java
@Component
public class GlobalSecurity extends WebSecurityConfigurerAdapter {


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.oauth2ResourceServer().jwt();
  }
}
```
##==##

<!-- .slide: class="exercice" -->
# Créer un serveur de ressource OAuth2
## Exercice 1 
<br>
Créer un serveur de ressource OAuth2 exposant 3 API:
<ul>
<li> une api publique</li>
<li> une api authentifié</li>
<li> une api nécessitant un scope admin</li>
</ul>

##==##

<!-- .slide: class="exercice" -->
# Créer un serveur de ressource OAuth2 Custom
## Exercice 2
<br>
Créer un serveur de ressource OAuth2 exposant 3 API:
<ul>
<li> une api publique</li>
<li> une api authentifié</li>
<li> une api nécessitant un scope admin</li>
</ul>

On veut vérifier certaines informations manuellemnt:
<ul>
<li>audience</li>
<li>issuer</li>
</ul>


