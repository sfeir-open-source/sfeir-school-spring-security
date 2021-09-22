# Exercice 4

## Énoncé
### 1. Utiliser plusieurs algorithmes de hachage
On veut utiliser plusieurs algorithmes de hachage dans notre application. Changer le code afin que soit supporté:
- `Pbkdf2PasswordEncoder` (`pbkdf2`) 
> Utiliser les paramètres suivant:
>  - secret: `secret`
>  - itérations: `10000`
>  - taille: `128`
- `BCrypt`                (`bcrypt`)
- `NoOpPasswordEncoder`   (`noop`)

Noter que l'algorithme de hachage par défaut est porté par `Pbkdf2PasswordEncoder`.

### 2. Sécuriser les mots de passe
Il faut changer les mots de passes en clair en utilisant l'algorithme par défaut (`Pbkdf2PasswordEncoder`).
- Seul les mots de passe en clair sont concernés (c'est à dire qui commencent par `{noop}`)
- Faire attention à bien prendre la partie après `{noop}`
- Vérifier que c'est bien transparent pour l'utilisateur: tout les utilisateurs doivent pouvoir continuer à utiliser leurs comptes

### 3. Sécuriser la mise à jour de mots de passe
Seul les utilisateurs avec un compte `ADMIN` peuvent effectuer cette opération (`PUT` `/password/update`).

### 4. Hacher les mots de passe des nouveaux utilisateurs
Changer le code afin que les nouveaux utilisateurs ai leur mot de passe haché par l'algorithme par défaut (`POST` `/register`).

## Classes à changer
>- `SchoolSecurityConfigurer`
>- `SchoolController`