# Tâche à réaliser

Créer un filtre permettant de gérer des requetes uudifié. Pour cela un UuidLookUpService est fourni

# Tips

- Renseigner la classe SecurityConfig
- Renseigner la classe RequestUnuuidifierFilter
- un uuid existe dans le code en dur pour commencer bb829bb5-a46a-42f6-bc35-03b942b4ad68 pour la param name avec la
  valeur toto

Ci-dessous la signature du service de gestion d'uuid, une méthode permet de dé-uuidifer la requete. une autre permet
d'ajouter des couples uuid/id

```java
    public String addValue(String name,String value)
public URI unuuidify(UriTemplate uriTemplate,String uri)
```

# Exemple du comportement attendu

GET /hello/bb829bb5-a46a-42f6-bc35-03b942b4ad68 renvoit toto POST /hello/{name} ajoutera un couple uuid/name dans
l'application, l'uuid est récupéré en retour du service. Appeler GET /hello/{uuid} renverra la nom enregistré
précedemment.
