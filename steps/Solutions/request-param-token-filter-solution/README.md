# Tâche à réaliser

Créer un filtre permettant de recevoir un token en query params et non dans le header Authorization.
De plus, on veut procéder manuellement à la validation de nos tokens. On va donc :
- créér un AuthenticationProvider custom, celui-ci devra être capable de recevoir un token oauth2
- récupérer la clé de signature
- valider cette signature et vérifier que le token n'est pas expiré

L'exercice fourni un JwkService pour récupérer la clé publique du token.

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
