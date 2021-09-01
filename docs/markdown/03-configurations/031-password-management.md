# La brique de mots de passe

<div class="full-center">
    <img src="./assets/images/2_password_encoder/password_encoder_1_base.png">
</div>

##==##
# Quelques définitions

<ul>
    <li class="fragment">Encoder</li>
    <li class="fragment">Crypter</li>
    <li class="fragment">Hacher</li>
</ul>

##==##
# Regardons un peu le code: PasswordEncoder

```java
public interface PasswordEncoder {
    
  String encode(CharSequence rawPassword);
  
  boolean matches(CharSequence rawPassword, String encodedPassword);
  
  default boolean upgradeEncoding(String encodedPassword) {
    return false;
  }
}
```

##==##
# Utiliser le bean PasswordEncoder

##==##
# Ce que fournit Spring Security

<ul>
    <li class="fragment">NoOpPasswordEncoder</li>
    <li class="fragment">BCryptPassword</li>
    <li class="fragment">SCryptPassword</li>
    <li class="fragment">et bien d'autres!</li>
</ul>

##==##
# Gérer plusieurs PasswordEncoder: DelegatingPasswordEncoder
