# La brique utilisateur

<div class="full-center">
    <img src="./assets/images/1_user_details_service/user_details_service_1_base.png">
</div>

##==##
# Regardons un peu le code

<div class="full-center">
    <img src="./assets/images/1_user_details_service/user_details_service_2_classes_base.png">
</div>

##==##
# Regardons un peu le code: UserDetails

<div class="full-center">
    <img src="./assets/images/1_user_details_service/user_details_service_3_classes_UserDetails.png">
</div>

##==##
# Regardons un peu le code: UserDetails

```java
public interface UserDetails extends Serializable {
  String getUsername();

  String getPassword();

  Collection<? extends GrantedAuthority> getAuthorities();

  boolean isAccountNonExpired();

  boolean isAccountNonLocked();

  boolean isCredentialsNonExpired();

  boolean isEnabled();
}
```

##==##
# Regardons un peu le code: GrantedAuthority

<div class="full-center">
    <img src="./assets/images/1_user_details_service/user_details_service_4_classes_GrantedAuthority.png">
</div>

##==##
# Regardons un peu le code: GrantedAuthority

```java
public interface GrantedAuthority extends Serializable {
    String getAuthority();
}
```

##==##
# Regardons un peu le code: un exemple d'implémentation 1

```java
@Entity
public class User implements UserDetails {
    
    @Id
    private int id;
    private String username;
    private String password;
    private String authority;
    
    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public String getAuthority() {
        return this.authority;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> this.authority);
    }
    // ...
}
```

##==##
# Regardons un peu le code: un exemple d'implémentation 1 - améliorations ?
<ul>
    <li class="fragment">séparation de responsabilités</li>
    <li class="fragment">gérer plusieurs sources d'utilisateurs</li>
</ul>

##==##
# Regardons un peu le code: un exemple d'implémentation 2

```java
@Entity
public class User {
  @Id
  private int id;
  private String username;
  private String password;
  private String authority;
  // ...
}
```

```java
public class SecurityUser implements UserDetails {
  private final User user;
  public SecurityUser(User user) {
    this.user = user;
}
  @Override
  public String getUsername() {
    return user.getUsername();
  }
  @Override
  public String getPassword() {
    return user.getPassword();
  }
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(() -> user.getAuthority());
  }
  // ...
}
```

##==##
# Regardons un peu le code: UserDetailsService

<div class="full-center">
    <img src="./assets/images/1_user_details_service/user_details_service_5_classes_UserDetailsService.png">
</div>

##==##
# Regardons un peu le code: UserDetailsService 

```java
public interface UserDetailsService {
  UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException;
}
```

##==##
# Regardons un peu le code: UserDetailsManager

<div class="full-center">
    <img src="./assets/images/1_user_details_service/user_details_service_6_classes_UserDetailsManager.png">
</div>

##==##
# Regardons un peu le code: UserDetailsManager
```java
public interface UserDetailsManager extends UserDetailsService {
  void createUser(UserDetails user);
  void updateUser(UserDetails user);
  void deleteUser(String username);
  void changePassword(String oldPassword, String newPassword);
  boolean userExists(String username);
}
```

##==##
# Utiliser le bean UserDetails
TODO: ajouter code snippet 

##==##
# Ce que fournit Spring Security

<ul>
    <li class="fragment">InMemoryUserDetailsManager</li>
    <li class="fragment">JdbcUserDetailsManager</li>
    <li class="fragment">LdapUserDetailsManager</li>
    <li class="fragment">CustomUserDetails</li>
</ul>
