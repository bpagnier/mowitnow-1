MowItNow
========

Ce dépôt contient une implantation du programme MowItNow.
C'est ma réponse à un exercice du même nom que le lecteur est censé déjà connaitre.

Choix techniques
================

## Java 
Pour écrire ce programme, j'ai choisi le langage Java. La raison principale est que c'est celui que je maitrise le mieux.
J'aurais aussi pu utiliser Scala que j'apprécie beaucoup, mais j'ai préféré, dans les circonstances de cet exercice m'éviter des erreurs par manque de pratique du langage utilisé.

## Git
Les sources sont gérées par Git et confiées à Github.
Les sources doivent être gérées par un outil de gestion qui permet le versionnement et le travail collaboratif. 
Git est le choix canonique actuel. Et Github permet de créer un dépôt public.

## Maven
Maven est utilisé comme outil de build du projet.
Sont utilisation facilite la gestion des dépendances et la production de l'artéfact final.
Il peut être construit facilement par une usine logicielle comme Jenkins.

## Les dépendances
Le programme est simple et court. Il ne possède que quelques dépendances. 
Je n'ai pas cherché à rajouter des dépendances inutiles pour montrer que ça existe (sauf dans un cas, peut-être :-)

### Guava
Guava est une bibliothèque de Google qui enrichit et complète la bibliothèque standard Java. Elle offre des
fonctionnalités manquantes et allège le code à produire. Jusqu'à Java 1.4, ce rôle était dévolu aux apache-commons.

### JCommander
C'est une bibliothèque développée par Cédric Beust qui permet d'alléger la gestion des paramètres de ligne de commande.
Elle est utilisée dans la classe MowItNow qui contient la méthode main et sert de lanceur.

### Guice
L'interface de gestion du système de tondeuse est proposée sous la forme d'un module Guice.
Certes, ce n'était pas nécessaire pour un programme de cette taille, mais ça montre que je connais cette
bibliothèque ainsi que les principes d'inversion de contrôle. 

### TestNG
Les tests unitaires sont développés avec TestNG. Écrit par Cédric Beust, cette bibliothèque est à mon sens plus 
sympa que JUnit dont elle a inspiré la version 4. De plus, elle est parfaitement prise en compte par Maven et les éditeurs.

### Easy testing fest assert
Offre une interface fluide pour écrire les assertions des tests unitaires.

Les classes principales
=======================

### MowItNow
Amorce du programme, elle contient la méthode principale.
Par défaut, le programme prend les commandes sur l'entrée standard et écrit les positions finales
dans la sortie standard. Ce comportement peut être modifié en précisant un fichier d'entré avec l'option "-i" ou "--input" ou un fichier de sorti avec l'option "-o" ou "--output". L'option "-c" ou "--charset" permet de changer le jeu de caractères utilisé. (La valeur par défaut US_ASCII convient dans tous les cas ici.) L'option "--help" vous rappellera ces commandes.

### MowerSystemControl
C'est l'interface de commande d'un système de tondeuse. 
Elle permet de définir la pelouse, ajouter des tondeuses et leur envoyer des ordres.
Sa principale implantation est MowItNowSystemControl.

### MowItNowFileParser
Parseur du fichier de commande (.mow), il transforme le contenu du fichier en une suite d'appels de services de MowerSystemControl.

