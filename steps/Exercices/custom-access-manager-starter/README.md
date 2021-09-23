# Tâche à réaliser

Créer un serveur de ressource permettant de gérer des accès dynamiques. 

Cela signifie que chaque route est affecté à un scope( un scope est un role ). On veut être capable d'ajouter des routes dans l'application à tout moment.

Pour ce faire on a besoin d'implémenter un access decision manager, celui fourni par spring security gérant des routes statiques.
Sont fournis :
- un controller de test avec 
  - une api /hello ==> scope hello
  - une api /time ==> scope time
  - une api /random/{randomPath}
  - une api GET /route permettant de récupérer la liste des routes
  - une api POST /route permettant d'ajouter une route, avec un exemple d'appel ci dessous
```
curl --location --request POST 'http://localhost:8443/route' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=0F31BD321CAAA10537D5A48C14C28354' \
--data-raw '{
"method" : "GET",
"uri" : "/random/scope1",
"scope" : "scope1"
}'
```
- un service RouteProvider permettant d'enregister/chercher une route et son scope
- une version à compléter nommé CustomAccessDecisionManager


# Tips

Ci-joint la requete postman pour générer une autorisation 
  - <scope demandé> peut être une valeur unique ou multiple séparé par des espace. Ex: 'scope1 scope2 scope3'

```
curl --location --request POST 'https://dev-56443628.okta.com/oauth2/aus1nztlwsqX4tYHg5d7/v1/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=client_credentials' \
--data-urlencode 'client_id=0oa1nzz7bmhQ2bsBJ5d7' \
--data-urlencode 'client_secret=Gfyol4hSu0PkO-T9y2eNGU7f1kjr4Kk_NPgRJZAD' \
--data-urlencode 'scope=<scope demandé>'
```
Liste des scopes disponibles sur le serveur avec lesquels vous pouvez tester :
- admin
- hello
- phone
- address
- email
- profile
- scope1
- scope2
- scope3
- scope4
- scope5
- scope6
- scope7
- scope8
