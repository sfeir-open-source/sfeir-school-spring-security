# Form Login

# Cas d'utilisation : Appeler une page de login

```java
@Component
public class GlobalSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login");
    }
}  
```

##==##

# Cas d'utilisation : Appeler un provider d'authentification externe

On utiliser le composant LoginUrlAuthenticationEntryPoint pour générer une url.
<br>
Pour ce faire, on peut utiliser la méthode suivante

```java
protected java.lang.String	determineUrlToUseForThisRequest​(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
```
##==##

# Exemple d'implémentation du composant

```java
public class CustomLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response,
                                                     AuthenticationException exception) {
        super.determineUrlToUseForThisRequest(request, response, exception);
        return getUrl();
    }

    private String getUrl() {
        //TODO call the authentication app to generate a login url    
    }
}
```

##==##
# Exemple d'injection du composant

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.authenticationEntryPoint(new CustomLoginUrlAuthenticationEntryPoint("/login"))
}
```


Source : 
https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/web/authentication/LoginUrlAuthenticationEntryPoint.html
