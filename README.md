# Bataille-JAVA
Projet effectué dans le cadre du cours JAVA -- L3 Projet Web & Mobile

## Instructions 
Le jeu se lance directement depuis la JVM 
ou 
depuis une console via les commandes suivantes:
  * `mvn compile` pour compiler l'application
  * `mvn exec:java -Dexec.mainClass="org.example.App` pour lancer l'application.
  
## Règles du jeu

 * On distribue les 52 cartes aux joueurs (peut se jouer à deux ou avec IA)
 * Chacun tire la carte du dessus de son paquet et la pose
 * Celui qui a la carte la plus forte ramasse les autres cartes.
 
Lorsque deux joueurs posent en même temps deux cartes de même valeur il y a "bataille". Lorsqu'il y a "bataille" les joueurs tirent la carte suivante et la    posent, face cachée, sur la carte précédente. Puis ils tirent une deuxième carte qu'ils posent cette fois-ci face découverte et c'est cette dernière qui départagera les joueurs.

Le gagnant est celui qui remporte toutes les cartes.

Dans ma version de jeu, il y a un score limite de 20. Le gagnant est donc celui qui remportera 20 cartes de l'autre joueur.


### Bon jeu ! 
