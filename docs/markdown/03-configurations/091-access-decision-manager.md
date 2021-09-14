# La brique de décision spring security par vote

<div class="full-center">
    <img src="./assets/images/08-access-decision-manager/access-decision-voting.png">
</div>

##==##

# L'interface access decision manager en détail

```java
void decide(Authentication authentication,Object secureObject,
  Collection<ConfigAttribute> attrs)throws AccessDeniedException;

  boolean supports(ConfigAttribute attribute);

  boolean supports(Class clazz);
```

##==##

# L'interface access decision manager en détail

```java
void decide(Authentication authentication,Object secureObject,
  Collection<ConfigAttribute> attrs)throws AccessDeniedException;
```

Implémente le process de prise de décision.

```java
public void decide(Authentication authentication,Object secureObject,
  Collection<ConfigAttribute> attrs)throws AccessDeniedException{
    boolean isAuthorized = checkAuthorization(authentication, secureObject)
    if (!isAuthorized){
        throw new AccessDeniedException("Authorization is missing");
    }
  }
```

##==##

# L'interface access decision manager en détail


<ul>
<li>

```java
boolean supports(ConfigAttribute attribute);
```
permet de définir si la classe est applicable au ConfigAttribute
</li>

<li>

```java
boolean supports(Class clazz);
```
permet de définir si la classe est applicable à l'objet de sécurité(jwt, username/password,...)</li>
</ul>

##==##

