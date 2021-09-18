# La brique de filtre

<div class="full-center">
    <img src="./assets/images/5_filter/filter_1_base.png">
</div>

##==##
# Définitions

<ul>
    <li class="fragment">Examine les requêtes (HTTP/HTTPS)</li>
    <li class="fragment">Point d'entrée de l'application</li>
    <li class="fragment">Chaîne de responsabilité</li>
</ul>

##==##
# Chaîne de responsabilité

<div class="full-center">
    <img src="./assets/images/5_filter/filter_2_chain_of_responsibility_base.png">
</div>

##==##
# Chaîne de responsabilité
## Le filtre 1 intercepte la requête

<div class="full-center">
    <img src="./assets/images/5_filter/filter_3_chain_of_responsibility_filter1_request.png">
</div>

##==##
# Chaîne de responsabilité
## Le filtre 1 ne valide pas la requête

<div class="full-center">
    <img src="./assets/images/5_filter/filter_4_chain_of_responsibility_filter1_reject.png">
</div>

##==##
# Chaîne de responsabilité
## Le filtre 2 intercepte la requête

<div class="full-center">
    <img src="./assets/images/5_filter/filter_5_chain_of_responsibility_filter2_request.png">
</div>

##==##
# Chaîne de responsabilité
## Le filtre 2 valide la requête

<div class="full-center">
    <img src="./assets/images/5_filter/filter_6_chain_of_responsibility_filter2_accept.png">
</div>

##==##
# Chaîne de responsabilité
## Le manager 2 gére l'authentification

<div class="full-center">
    <img src="./assets/images/5_filter/filter_7_chain_of_responsibility_manager2.png">
</div>

##==##
# Ajouter un filtre

<ul>
    <li class="fragment">Créer une classe implémentant la classe Filter</li>
    <li class="fragment">Surcharger la méthode <i>doFilter()</i></li>
    <li class="fragment">Ajouter aux autres filtres</li>
</ul>

##==##
# Ajouter un filtre: implémentation

```java
public class CustomFilter { 
}
```

##==##
# Ajouter un filtre: implémentation

```java
public class CustomFilter implements Filter { 
}
```

##==##
# Ajouter un filtre: implémentation

```java
public class CustomFilter implements Filter { 
    
  @Override
  public void doFilter() throws IOException, ServletException {
  } 
}
```

##==##
# Ajouter un filtre: implémentation

```java
public class CustomFilter implements Filter { 
    
  @Override
  public void doFilter(
     ServletRequest request
  ) throws IOException, ServletException {
  } 
}
```

##==##
# Ajouter un filtre: implémentation

```java
public class CustomFilter implements Filter { 
    
  @Override
  public void doFilter(
     ServletRequest request, 
     ServletResponse response
  ) throws IOException, ServletException {
  } 
}
```

##==##
# Ajouter un filtre: implémentation

```java
public class CustomFilter implements Filter { 
    
  @Override
  public void doFilter(
     ServletRequest request, 
     ServletResponse response, 
     FilterChain filterChain 
  ) throws IOException, ServletException {
  } 
}
```

##==##
# Ajouter un filtre: implémentation

```java
public class CustomFilter implements Filter { 
    
  @Override
  public void doFilter(
     ServletRequest request, 
     ServletResponse response, 
     FilterChain filterChain 
  ) throws IOException, ServletException {
    var httpRequest = (HttpServletRequest) request; 
    var httpResponse = (HttpServletResponse) response; 
    String requestId = httpRequest.getHeader("Request-Id");
  } 
}
```

##==##
# Ajouter un filtre: implémentation

```java
public class CustomFilter implements Filter { 
    
  @Override
  public void doFilter(
     ServletRequest request, 
     ServletResponse response, 
     FilterChain filterChain 
  ) throws IOException, ServletException {
    var httpRequest = (HttpServletRequest) request; 
    var httpResponse = (HttpServletResponse) response; 
    String requestId = httpRequest.getHeader("Request-Id");
    
    if (requestId == null || requestId.isBlank()) { 
      httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }
  } 
}
```

##==##
# Ajouter un filtre: implémentation

```java
public class CustomFilter implements Filter { 
    
  @Override
  public void doFilter(
     ServletRequest request, 
     ServletResponse response, 
     FilterChain filterChain 
  ) throws IOException, ServletException {
    var httpRequest = (HttpServletRequest) request; 
    var httpResponse = (HttpServletResponse) response; 
    String requestId = httpRequest.getHeader("Request-Id");
    
    if (requestId == null || requestId.isBlank()) { 
      httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }
    
    filterChain.doFilter(request, response); 
  } 
}
```

##==##
# Ajouter un filtre: implémentation

```java
@Configuration
public class SchoolSecurityConfigurer extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .addFilterBefore(new CustomFilter(), BasicAuthenticationFilter.class)
      .authorizeRequests()
      .anyRequest()
      .authenticated();
  } 
}
```

##==##
# La classe OncePerRequestFilter

<ul>
    <li class="fragment">Une seule fois par requête</li>
    <li class="fragment">Les requêtes sont en HTTP</li>
    <li class="fragment">Surcharge de méthodes (exemple: shouldNotFilter(HttpServletRequest))</li>
</ul>

##==##
# La classe OncePerRequestFilter

<ul>
    <li>Une seule fois par requête</li>
    <li>Les requêtes sont en HTTP</li>
    <li>Surcharge de méthodes (exemple: shouldNotFilter(HttpServletRequest))</li>
</ul>

```java
public class AuthenticationLoggingFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String requestId = request.getHeader("Request-Id");
        // Some code...
        filterChain.doFilter(request, response);
    }
}
```
<b>Important : Ne jamais oublier la ligne filterChain.doFilter(request, response)</b>

##==##
# Ordre des filtres

<div class="full-center">
    <img src="./assets/images/5_filter/filter_8_order.png">
</div>

##==##
# Ordre des filtres

<div class="full-center">
    <img src="./assets/images/5_filter/filter_9_order.png">
</div>

##==##

<!-- .slide: class="exercice" -->
# Créer un filtre permettant de gérer des UUID dans les url
## Exercice
<br>
On a un serveur qui veut obfusquer ses id internes ( ex open banking ) :
<ul>
<li> lire une requete contenant un uuid et transformer en id</li>
<li> générer un uuid rattaché à un id interne</li>
</ul>

<br>
Objectif de l'exercice :
<ul>
<li> créér un filtre qui gère ce problème</li>
<li> injecter ce filtre dans la configuration spring security</li>
</ul>

##==##
