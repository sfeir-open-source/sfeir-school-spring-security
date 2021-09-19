# Tâche à réaliser

Créer un serveur de ressource avec permettant de gérer des accès dynamiques. On utilisera un custom access decision
manager. Sont fourni, un controller de test et un service RouteProvider permettant d'enregister/chercher une route et
son scope

# Tips

Ci-joint la requete postman pour générer une autorisation

```
curl --location --request POST 'https://dev-56443628.okta.com/oauth2/aus1nztlwsqX4tYHg5d7/v1/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=client_credentials' \
--data-urlencode 'client_id=0oa1nzz7bmhQ2bsBJ5d7' \
--data-urlencode 'client_secret=Gfyol4hSu0PkO-T9y2eNGU7f1kjr4Kk_NPgRJZAD' \
--data-urlencode 'scope=<scope requis>'
```
