# La brique de filtre

<div class="full-center">
    <img src="./assets/images/5_filter/filter_1_base.png">
</div>

##==##
# Définitions

<ul>
    <li class="fragment">Examine les requêtes (HTTP/HTTPS)</li>
    <li class="fragment">Point d'entrée de l'application</li>
    <li class="fragment">Chaîne de responsabilité</li>
</ul>

##==##
# Chaîne de responsabilité

<div class="full-center">
    <img src="./assets/images/5_filter/filter_2_chain_of_responsibility_base.png">
</div>

##==##
# Chaîne de responsabilité
## Le filtre 1 intercepte la requête

<div class="full-center">
    <img src="./assets/images/5_filter/filter_3_chain_of_responsibility_filter1_request.png">
</div>

##==##
# Chaîne de responsabilité
## Le filtre 1 ne valide pas la requête

<div class="full-center">
    <img src="./assets/images/5_filter/filter_4_chain_of_responsibility_filter1_reject.png">
</div>

##==##
# Chaîne de responsabilité
## Le filtre 2 intercepte la requête

<div class="full-center">
    <img src="./assets/images/5_filter/filter_5_chain_of_responsibility_filter2_request.png">
</div>

##==##
# Chaîne de responsabilité
## Le filtre 2 valide la requête

<div class="full-center">
    <img src="./assets/images/5_filter/filter_6_chain_of_responsibility_filter2_accept.png">
</div>

##==##
# Chaîne de responsabilité
## Le manager 2 gére l'authentification

<div class="full-center">
    <img src="./assets/images/5_filter/filter_7_chain_of_responsibility_manager2.png">
</div>

##==##
# Ajouter un filtre

<ul>
    <li class="fragment">Créer une classe implémentant la classe Filter</li>
    <li class="fragment">Surcharger la méthode <i>doFilter()</i></li>
    <li class="fragment">Ajouter aux autres filtres</li>
</ul>

##==##
# Ajouter un filtre: implémentation

##==##
# La classe OncePerRequestFilter

##==##
# Ordre des filtres

<div class="full-center">
    <img src="./assets/images/5_filter/filter_8_order.png">
</div>

##==##
# Ordre des filtres

<div class="full-center">
    <img src="./assets/images/5_filter/filter_9_order.png">
</div>
