# 1. Qu'est ce que Spring Security ?

- Framework fourni par Spring
- Permet de sécuriser des applications Java/J2EE
- Fournit une protection de base contre les failess courantes
  - CORS
  - CSRF
  - injection (sql, ...)
  - ...
- S'interface avec les principales briques authentification/autorisation du marché

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
