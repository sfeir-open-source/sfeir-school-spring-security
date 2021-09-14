# La brique de contexte

<div class="full-center">
    <img src="./assets/images/6_security_context/security_context_1_base.png">
</div>

##==## 
# L'interface SecurityContext

```java
public interface SecurityContext extends Serializable {
  Authentication getAuthentication();
  void setAuthentication(Authentication authentication);
}
```

##==##
# Récupérer les informations de l'utilisateur connecté
## SecurityContextHolder
```java
@GetMapping("/user")
public String getUserName() {
}
```
##==##
# Récupérer les informations de l'utilisateur connecté
## SecurityContextHolder
```java
@GetMapping("/user")
public String getUserName() {
  SecurityContext context = SecurityContextHolder.getContext();
}
```
##==##
# Récupérer les informations de l'utilisateur connecté
## SecurityContextHolder
```java
@GetMapping("/user")
public String getUserName() {
  SecurityContext context = SecurityContextHolder.getContext();
  Authentication authentication = context.getAuthentication();
}
```
##==##
# Récupérer les informations de l'utilisateur connecté
## SecurityContextHolder
```java
@GetMapping("/user")
public String getUserName() {
  SecurityContext context = SecurityContextHolder.getContext();
  Authentication authentication = context.getAuthentication();
  return authentication.getName();
}
```
##==##

# Récupérer les informations de l'utilisateur connecté
## SecurityContextHolder
```java
@GetMapping("/user")
public String getUserName() {
  SecurityContext context = SecurityContextHolder.getContext();
  Authentication authentication = context.getAuthentication();
  return authentication.getName();
}
```
## Authentification
```java
@GetMapping("/user")
public String getUserName() {
}
```

##==##

# Récupérer les informations de l'utilisateur connecté
## SecurityContextHolder
```java
@GetMapping("/user")
public String getUserName() {
  SecurityContext context = SecurityContextHolder.getContext();
  Authentication authentication = context.getAuthentication();
  return authentication.getName();
}
```
## Authentification
```java
@GetMapping("/user")
public String getUserName(Authentication authentication) {
}
```

##==##

# Récupérer les informations de l'utilisateur connecté
## SecurityContextHolder
```java
@GetMapping("/user")
public String getUserName() {
  SecurityContext context = SecurityContextHolder.getContext();
  Authentication authentication = context.getAuthentication();
  return authentication.getName();
}
```
## Authentification
```java
@GetMapping("/user")
public String getUserName(Authentication authentication) {
    return authentication.getName();
}
```
