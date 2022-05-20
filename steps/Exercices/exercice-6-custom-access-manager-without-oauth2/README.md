pom.xml# Tâche à réaliser

Créer un custom authentication filter capable de donner accès aux opérations uniquement si l'authetification est fait via le httpBasic
Un utilisateur authentifié et possédant les droits read doit pouvoir avoi accès de base à  
* /hello
* /time

Un utilisateur authentifié doit pouvoir ajouter une nouvelle route en commençant le chemain de celle-ci par /random
ex:
{
"method":"get",
"uri": "/random/test",
"scope":"read"
}
via une requête post
suivant les paramètres on y accédera alors ainsi
http://localhost:8443/random/test

# Tests
Créer une série de tests unitaire permettant de prendre en compte l'authentification de l'utilisateur

# Bonus
Transformer l'application en serveur de ressources et permettre uniquement aux utilisateurs s'etant authentifié via un serveur d'authorisation de créer de nouvelles routes.
