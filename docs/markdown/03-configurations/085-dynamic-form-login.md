# Form Login : provider externe

# Cas d'utilisation

Appeler un provider externe, ex la mire de login de l'entreprise

On utilisera le composant LoginUrlAuthenticationEntryPoint. 

```java
void	afterPropertiesSet()	 
protected java.lang.String	buildHttpsRedirectUrlForRequest​(javax.servlet.http.HttpServletRequest request)
protected java.lang.String	buildRedirectUrlToLoginPage​(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, AuthenticationException authException)	 
void	commence​(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, AuthenticationException authException)
protected java.lang.String	determineUrlToUseForThisRequest​(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, AuthenticationException exception)
java.lang.String	getLoginFormUrl()	 
protected PortMapper	getPortMapper()	 
protected PortResolver	getPortResolver()	 
protected boolean	isForceHttps()	 
protected boolean	isUseForward()	 
void	setForceHttps​(boolean forceHttps)
void	setPortMapper​(PortMapper portMapper)	 
void	setPortResolver​(PortResolver portResolver)	 
void	setUseForward​(boolean useForward)
```
##==##

# Exemple d'implémentation

```java
@Override
protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response,
  AuthenticationException exception) {
  super.determineUrlToUseForThisRequest(request, response, exception);
  return getUrl();
  }
  
private String getUrl(){
    //TODO call the authentication app to generate a login url    
}
```

Source : 
https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/web/authentication/LoginUrlAuthenticationEntryPoint.html
