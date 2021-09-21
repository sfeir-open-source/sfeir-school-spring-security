# Exercice 2

## Énoncé
Le but de cet exercice est de sécuriser des resources selon les règles suivantes:

1. L'url `/public` est accessible par tout le monde (authentifié ou non)
2. L'url `/anonyme` est accessible par tout le monde, à condition d'être authentifié
3. L'url `/private` n'est accessible que par les utilisateurs authentifiés et ayant un rôle `ADMIN` ou `SFEIR`
4. L'url `/forbidden` et `/access-denied` ne sont accessibles par personne
5. 
   1. Les urls commencant par `/admin` sont accessibles seulement par les utilisateurs ayant le rôle `ADMIN`...
   2. ... sauf si l'url contient `forbidden`, dans ce cas elle est inaccessible (ex: `admin/a/forbidden/b/c`)
6. 
   1. Les urls commencant par `/sfeir` sont accessibles par les utilisateurs ayant un rôle `ADMIN` ou `SFEIR`...
   2. ... sauf si elles commencent par `/sfeir/special`: elles sont accessibles seulement par les utilisateurs `SFEIR` mais pas les utilisateurs `ADMIN`
7. Les urls contenant les codes `us`, `au`, `ca` ou `uk` sont visibles par tout le monde (par exemple `/resource/us/view` ou `/uk/a/b/c`)
8. Les resources dont:
   - l'url commence par `/resource/sensitive`,
   - le chemin a pour taille 3 (exemple: s'applique à `/resource/sensitive/exemple` mais pas à `/resource/sensitive` ou à `/resource/sensitive/test1/test2`)
   - la méthode HTTP est `POST`

ne sont pas accessibles
9. Toutes les autres requêtes sont accessibles pour les personnes authentifiées

> Changer la méthode `configure()` dans la classe `security.SchoolSecurityConfigurer`.

### Rappel
Restez attentif à l'ordre des filtres

