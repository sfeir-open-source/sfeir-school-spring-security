# Tâche à réaliser

Créer un serveur de ressource permettant de gérer des accès dynamiques. On utilisera un custom access decision
manager. Sont fournis :
- un controller de test
- un service RouteProvider permettant d'enregister/chercher une route et son scope


Liste des scopes disponibles sur le serveur :
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

La dépendance oauth2

```xml
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
    </dependency>
```
