<!-- .slide: class="exercice" -->
# Créer un filtre pour gérer un token en query params
## Exercice 2
<br>
On veut s'interfacer avec une api legacy :
<ul>
<li> on reçoit le token en query param</li>
<li> valider ce token</li>
<li> utiliser notre propre authentication manager pour token jwt oauth2</li>
</ul>
Objectif de l'exercice :
<ul>
<li>créer un filtre capable d'authentifier via un token oauth2</li>
<li>créer un AuthenticationProvider capable de valider un token oauth2</li>
<li>injecter les composants dans la configuration spring security</li>
</ul>

##==##

<!-- .slide: class="exercice" -->
# Créer un serveur de d'autorisation oauth2
## Exercice 3
<br>
Créer un serveur d'autorisation:
<ul>
<li> capable d'emettre des token client_credentials</li>
</ul>

Objectif de l'exercice :
<ul>
<li>créer un serveur d'autorisation</li>
<li>découvrir comment faire un serveur d'autorisation avec spring security</li>
</ul>

##==##


