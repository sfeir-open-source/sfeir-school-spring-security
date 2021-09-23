# 1. Qu'est ce que Spring Security ?

<ul>
  <li class="fragment">Framework fourni par Spring</li>
  <li class="fragment">Permet de sécuriser des applications Java/J2EE</li>
  <li class="fragment">Fournit une protection de base contre les failles courantes</li>
  <ul>
    <li class="fragment">CORS</li>
    <li class="fragment">CSRF</li>
    <li class="fragment">Injections (SQL, etc.)</li>
    <li class="fragment">et bien d'autres</li>
  </ul>
  <li class="fragment">Construit autour de modules</li>
  <li class="fragment">Principe de surcharge</li>
  <li class="fragment">Boite à outils: authentification/autorisation, hachage de mot de passes, etc.</li>
</ul>

##==##
# 2. Les briques de base de Spring Security

<div class="full-center">
    <img src="./assets/images/architecture_0_base.png">
</div>

##==##
# Traitement d'une requête: étape 1

<div class="full-center">
    <img src="./assets/images/architecture_1_filter.png">
</div>

##==##
# Traitement d'une requête: étape 2

<div class="full-center">
    <img src="./assets/images/architecture_2_authentication_manager.png">
</div>

##==##
# Traitement d'une requête: étape 3

<div class="full-center">
    <img src="./assets/images/architecture_3_authentication_provider.png">
</div>

##==##
# Traitement d'une requête: étape 4

<div class="full-center">
    <img src="./assets/images/architecture_4_user_password.png">
</div>

##==##
# Traitement d'une requête: étape 5

<div class="full-center">
    <img src="./assets/images/architecture_5_filter.png">
</div>

##==##
# Traitement d'une requête: étape 6

<div class="full-center">
    <img src="./assets/images/architecture_6_security_context.png">
</div>
