# Tâche à réaliser

Créer un serveur d'authorization basique permettant d'émettre des token de type client_credentials.

Créer un serveur nécessite 2 composants :
  - un composant étendant AuthorizationServerConfigurerAdapter
  - un composant étendant ClientDetailsService


# Tips

Ci-joint la libraire spring nécessaire pour la partie serveur d'autorisation

```xml
        <dependency>
            <groupId>org.springframework.security.oauth</groupId>
            <artifactId>spring-security-oauth2</artifactId>
            <version>2.3.6.RELEASE</version>
        </dependency>
```
