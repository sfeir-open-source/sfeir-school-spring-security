# Access decision manager

Un access decision manager a la responsabilité de décider si un accès est autorisé
<br/>
C'est l'équivalent de l'authentication manager mais pour la partie autorisation

##==##

# Access decision manager

L'implémentation par défaut :
<ul>
    <li class="fragment">Reçoit la requête</li>
    <li class="fragment">Détermine si elle est habilitée à traiter cette autorisation</li>
    <li class="fragment">Utilise un système de voters pour gérer les différents cas</li>
    <li class="fragment">Accepte ou refuse l'accès</li>
</ul>

##==##

# La brique de décision spring security par vote

<div class="full-center">
    <img src="assets/images/08-access-decision-manager/access-decision-voting.png">
</div>

##==##


# Les voters

Exemples de Voter par défaut

<ul>
    <li class="fragment">AuthenticatedVoter, différencie entre user anonyme, authentifié, remember-me users, ...</li>
    <li class="fragment">RoleVoter, autorise/refuse sur la présence d'un attribut role.</li>
    <li class="fragment">Et bien d'autres, qui s'applique à des cas spécifiques ou complexes</li>
</ul>

##==##

# Voters : cas du user anonyme

```java
@Override
protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
        .anyRequest()
        .authenticated();
}
```

<div class="full-center">
    <img src="assets/images/08-access-decision-manager/blank-decision.png">
</div>

##==##


# Voters : cas du user anonyme

```java
@Override
protected void configure(HttpSecurity http) throws Exception{
  http.authorizeRequests()
  .anyRequest()
  .authenticated();
  }
```

<div class="full-center">
    <img src="assets/images/08-access-decision-manager/anonyme.png">
</div>

##==##

# Voters : cas du user qui accède une ressource derrière un role

```java
@Override
protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
        .anyRequest()
        .hasRole("read");
}
```

<div class="full-center">
    <img src="assets/images/08-access-decision-manager/blank-decision.png">
</div>

##==##

# Voters : cas du user qui accède une ressource derrière un role

```java
@Override
protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
        .anyRequest()
        .hasRole("read");
}
```

<div class="full-center">
    <img src="assets/images/08-access-decision-manager/role.png">
</div>

##==##

# L'interface access decision manager en détail

<ul>
<li class="fragment">

```java
void decide(Authentication authentication,Object secureObject,
  Collection<ConfigAttribute> attrs)throws AccessDeniedException;
```

Implémente le process de prise de décision.

```java
public void decide(Authentication authentication,Object secureObject,
  Collection<ConfigAttribute> attrs)throws AccessDeniedException{
    boolean isAuthorized=checkAuthorization(authentication,secureObject)
    if(!isAuthorized){
        throw new AccessDeniedException("Authorization is missing");
     }
}
```

</li>

<li class="fragment">

```java
boolean supports(ConfigAttribute attribute);
```

permet de définir si la classe est applicable au ConfigAttribute

</li>

<li class="fragment">

```java
boolean supports(Class clazz);
```

permet de définir si la classe est applicable à l'objet de sécurité(jwt, username/password,...)

</li>
</ul>


##==##

# Exemple complet

```java
public class CustomAccessDecisionManager implements AccessDecisionManager {
    
  @Override
  public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
      if (!isAuthorized()) {
        throw new AccessDeniedException("Access Denied");
      }
      //si on ne retourne pas d'exception, l'accès est autorisé
  }

  @Override
  public boolean supports(ConfigAttribute attribute) {
    return true;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return FilterInvocation.class.isAssignableFrom(clazz);
  }
}
```

##==##

<!-- .slide: class="exercice" -->

# Créer un access decision manager pour routes dynamiques

## Exercice

On veut gérer les accès aux routes de manière dynamiques :
<li>utiliser le RouteProvider fourni dans l'exercice</li>
<li>Une route est défini par un verbe et une uri, associé à un scope donné ( ex GET /toto requiert le scope toto)</li>
<br>
Objectif de l'exercice :
<ul>
<li>créer un composant qui est capable de déterminer dynamiquement si une route est autorisé</li>
<li>injecter le composant dans la configuration spring security</li>
</ul>

##==##

