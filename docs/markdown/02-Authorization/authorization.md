<!-- .slide: class="transition bg-pink" -->

# Authorization

L'auhtorization est le processus qui permet à un utilisateur préalablement authentifié 
(Authentification) de se voir attribué des droits dans l'application

Par exemple, droits de lecture, écriture, admin, ...

Présentation de l' AccessDecisionManager

```java
void	decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes);
boolean	supports(Class<?> clazz);
boolean	supports(ConfigAttribute attribute);
```

AccessDecisionVoter

```java
boolean supports(ConfigAttribute attribute);

boolean supports(Class<?> clazz);

int vote(Authentication authentication, S object, Collection<ConfigAttribute> attributes);
```

//TODO custom access decision manager exercice
