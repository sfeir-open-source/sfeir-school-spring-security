# Oauth2 : un framework d'autorisation

Oauth 2 est un framework d'autorisation, et non d'authentification
</br> ==> Ne pas confondre avec OpenID Connect

Source : https://datatracker.ietf.org/doc/html/rfc6749

Une autorisation est défini par les éléments suivants: 
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

# Resource Server

Le serveur qui possède la donnée à laquelle on veut accéder (Rest apis par exemple)

##==##

# Authorization Server

Le serveur qui autorise un client à accéder à un jeu de ressources donnée ( un serveur social comme google/facebook,...).
Il émettra pour ce faire un token d'autorisation .

##==##

# Client

Le client est le système qui va demander l'accès aux ressources (site web, app mobile, ...)

##==##

# Resource Owner

L'utilisateur ou application qui possède les ressources que l'on veut sécuriser ( l'utilisateur ).
Il peut donner un consentement pour accéder à ces données
##==##

# Scopes

Dans le jargon oauth2, un scope = un droit/role.
Un serveur d'autorisation délivre les scopes autorisé par le ressource owner ou sa propre configuration
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
Un jwt peut aussi être signé et/out chiffré.</li>
</ul>

##==##


# Oauth2 flow

<div class="full-center">
    <img src="./assets/images/7-oauth2/oauth-flow2.png">
</div>

##==##

<!-- .slide: class="exercice" -->
# Créer un serveur de ressource oauth2
## Exercice 1 
<br>
Créer un serveur de ressource oauth2 exposant 3 API:
<ul>
<li> une api publique</li>
<li> une api authentifié</li>
<li> une api nécessitant un scope admin</li>
</ul>


