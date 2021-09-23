# Tâche à réaliser

Créer un filtre permettant de gérer des requetes uudifié. Pour cela un UuidLookUpService est fourni
- Créer un filtre
- Ajouter le filtre dans la config spring security
- Utiliser le controller fourni dans le starter
- un uuid existe dans le code en dur :
  bb829bb5-a46a-42f6-bc35-03b942b4ad68 pour la param name avec la valeur toto

# Tips

Ci-dessous la signature du service de gestion d'uuid, une méthode permet de dé-uuidifer la requete. une autre permet d'ajouter des couples
uuid/id

```java
    public String addValue(String name,String value)
public URI unuuidify(UriTemplate uriTemplate,String uri)
```
