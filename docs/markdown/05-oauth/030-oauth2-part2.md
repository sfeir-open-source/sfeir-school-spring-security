# Démo: Créer un filtre pour gérer un token en query params
## Objectif : s'interfacer avec une api legacy
On veut  :
<ul>
<li> on reçoit le token en query param</li>
<li> valider ce token</li>
<li> utiliser notre propre authentication manager pour token jwt OAuth2</li>
<ul>

##==##

# Démo: Créer un filtre pour gérer un token en query params
## Les étapes à réaliser :
</ul>
<li >créer un filtre capable d'authentifier via un token OAuth2</li>
<li>créer un AuthenticationProvider capable de valider un token OAuth2</li>
<li>injecter les composants dans la configuration Spring Security</li>
</ul>

##==##

<!-- .slide: class="exercice" -->
# Créer un serveur de d'autorisation OAuth2
## Exercice 3
<br>
Créer un serveur d'autorisation:
<ul>
<li> capable d'emettre des token client_credentials</li>
</ul>

Objectif de l'exercice :
<ul>
<li>créer un serveur d'autorisation</li>
<li>découvrir comment faire un serveur d'autorisation avec Spring Security</li>
</ul>


