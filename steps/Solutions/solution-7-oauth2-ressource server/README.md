# Tâche à réaliser

Créer un controller  3 api
- une api /publique
- une api /secure
- une api /super-secure

Créer le security config pour protéger ces 3 api via un serveur de ressource :
- /publique est ouverte
- /secure nécessite d'être authentifié
- /super-secure nécessitant un droit admin


# Tips

Ci-joint la requete postman pour générer une autorisation

```
curl --location --request POST 'https://dev-56443628.okta.com/oauth2/aus1nztlwsqX4tYHg5d7/v1/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=client_credentials' \
--data-urlencode 'client_id=0oa1nzz7bmhQ2bsBJ5d7' \
--data-urlencode 'client_secret=Gfyol4hSu0PkO-T9y2eNGU7f1kjr4Kk_NPgRJZAD' \
--data-urlencode 'scope=admin'
```

La dépendance oauth2 à utiliser pour créer un serveur de ressource

```xml
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
    </dependency>
```
