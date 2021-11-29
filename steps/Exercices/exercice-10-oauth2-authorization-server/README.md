# Tâche à réaliser

Créer un serveur d'authorization basique permettant d'émettre des token de type client_credentials.

Créer un serveur nécessite 2 composants :
  - un composant étendant AuthorizationServerConfigurerAdapter à remplir
    - Les méthodes à surcharger sont présentes, seules manque des éléments de configuration à fournir
  - un composant étendant ClientDetailsService à finaliser, il fournit un client par défaut dans le constructeur


# Tips

Ci-joint la libraire spring nécessaire pour la partie serveur d'autorisation

```xml
        <dependency>
            <groupId>org.springframework.security.oauth</groupId>
            <artifactId>spring-security-oauth2</artifactId>
            <version>2.3.6.RELEASE</version>
        </dependency>
```

Pas de controler, les endpoints sont fournis par le framework, voici un exemple d'appel au serveur pour requeter un token
```
curl --location --request POST 'http://localhost:8081/oauth/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'client_id=dummy-client' \
--data-urlencode 'grant_type=client_credentials' \
--data-urlencode 'client_secret=dummy-password'
```
