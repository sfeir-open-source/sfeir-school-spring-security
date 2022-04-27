#Scenario
Ajouter un filtre à l'application afin que seul les utilisateurs connaissant le nombre magique puissent y accéder.

## Technique
Le nombre magique a la valeur de 42.  
Il sera présent dans le header de la requête.  
Si ce nombre n'est pas présent, la requête est renvoyé avec un code d'erreur 403.  
