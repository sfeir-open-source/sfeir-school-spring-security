# Exercice 3

## Énoncé
Dans la démo, nous avons utilisé la brique JDBC proposée par Spring Security pour récupérer nos utilisateurs.
Nous souhaitons maintenant les récupérer avec la brique utilisateur "custom".

Pour se faire, changer les classes:
>- `SchoolUserDetails`
>- `SchoolUserDetailsService`

Pour vérifier votre code, appeler la resource `/private` avec l'un des utilisateurs inserés:

|Username  |Password  |
|----------|----------|
|admin     |admin     |
|tutor     |sfeir     |
|student   |student   |

### Exemple
Appeler la resource `/private` avec l'utilisateur `admin`
```bash
curl -u admin:admin http://localhost:8080/private
```
La console affiche bien `Welcome admin!`
