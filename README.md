# Application Chat Motivant et API

## Introduction

Cette application se compose de deux parties principales :

- L'**API** : Fournit des fonctionnalités pour récupérer des citations inspirantes et gérer les utilisateurs.
- Le **Chat** : Permet aux utilisateurs d'interagir avec un chatbot motivant.
L'objectif principal de l'application est d'encourager et de motiver les utilisateurs à travers des conversations et des citations inspirantes.

## Prérequis

Avant de commencer, assurez-vous d'avoir installé les éléments suivants sur votre machine :

- **Java** : La version requise est Java 8 ou supérieure.
- **Spring Boot** : Utilisé pour développer l'application API.
- **MySQL** : Pour la gestion de la base de données.
- **Thymeleaf** : Pour le rendu des pages HTML côté serveur.

## Installation

### 1. Cloner le dépôt
Clonez le dépôt de l'application depuis le dépôt Git :


``` bash
git clone https://github.com/odakris/sdv-chat-motivational-api.git
```

### 2. Configurer la base de données

Assurez-vous que MySQL est installé et en cours d'exécution sur votre machine. Vous devez créer une base de données et ajouter les tables nécessaires à l'application.

Copiez et exécutez le script suivant dans votre environnement MySQL :

```SQL
-- Créer la base de données si elle n'existe pas déjà
CREATE DATABASE IF NOT EXISTS motivational_database;

-- Utiliser la base de données nouvellement créée
USE motivational_database;

-- Créer la table quote si elle n'existe pas déjà
CREATE TABLE IF NOT EXISTS quote (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quote_text VARCHAR(255) NOT NULL
);

-- Insertion de données dans la table quote
INSERT INTO quote (quote_text) VALUES ('Le seul moyen de faire du bon travail est d\'aimer ce que vous faites.');
INSERT INTO quote (quote_text) VALUES ('Ce n\'est pas la montagne que nous conquérons, mais nous-mêmes.');
INSERT INTO quote (quote_text) VALUES ('L\'échec n\'est qu\'un passage vers la réussite.');
INSERT INTO quote (quote_text) VALUES ('Rien de grand n\'a jamais été accompli sans enthousiasme.');
INSERT INTO quote (quote_text) VALUES ('Les rêves ne fonctionnent que si vous travaillez dur.');
INSERT INTO quote (quote_text) VALUES ('L\'inspiration existe, mais elle doit vous trouver en train de travailler.');
INSERT INTO quote (quote_text) VALUES ('N\'attendez pas le moment parfait, prenez simplement le moment et rendez-le parfait.');
INSERT INTO quote (quote_text) VALUES ('Votre vie ne s\'améliore pas par hasard, elle s\'améliore par le changement.');
INSERT INTO quote (quote_text) VALUES ('Ne vous arrêtez pas quand vous êtes fatigué, arrêtez-vous quand vous avez terminé.');
INSERT INTO quote (quote_text) VALUES ('Le succès est la somme de petits efforts, répétés jour après jour.');
INSERT INTO quote (quote_text) VALUES ('Ce que vous faites aujourd\'hui peut améliorer tous vos lendemains.');
INSERT INTO quote (quote_text) VALUES ('La meilleure façon de prédire l\'avenir est de le créer.');
INSERT INTO quote (quote_text) VALUES ('Ne rêvez pas votre vie, vivez vos rêves.');
INSERT INTO quote (quote_text) VALUES ('L\'imagination est plus importante que la connaissance.');
INSERT INTO quote (quote_text) VALUES ('Vous ne trouvez pas le bonheur, vous le créez.');
INSERT INTO quote (quote_text) VALUES ('Les grandes choses ne sont jamais faites par une seule personne, elles sont faites par une équipe de personnes.');
INSERT INTO quote (quote_text) VALUES ('Il n\'est jamais trop tard pour être ce que vous auriez pu être.');
INSERT INTO quote (quote_text) VALUES ('Ne soyez pas intimidé par ce que vous ne savez pas.');
INSERT INTO quote (quote_text) VALUES ('Tout ce que vous pouvez imaginer est réel.');
INSERT INTO quote (quote_text) VALUES ('La seule limite à notre réalisation de demain sera nos doutes et hésitations d\'aujourd\'hui.');

-- Créer la table conversation si elle n'existe pas déjà
CREATE TABLE IF NOT EXISTS conversation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    response TEXT NOT NULL, 
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

Cela va créer deux tables :

- **quote** : Contient les citations inspirantes.
- **conversation** : Contient l'historique des conversations entre les utilisateurs et le chatbot.

## Utilisation de l'application

### 1. API

Dans le dossier `motivational-api` se trouve l'application fournissant L'API permettant de générer des citations aléatoires.

`GET /getQuote` : Cette route renvoie une citation inspirante aléatoire à chaque appel.

Lancer l'application et il sera possible de faire des appels avec postman avec l'url `http://localhost:8081/`

### 2. Le Chat

L'application permet aussi à l'utilisateur d'engager une conversation avec le chatbot.
Dans le dossier `motivational-chat` se trouve l'application de chat faisant appel à l'API. `(http://localhost:8080/)`

- **Formulaire de conversation** - `/conversation`: L'utilisateur peut envoyer un message et recevoir une réponse motivante du chatbot. Le message de l'utilisateur et la réponse du chatbot sont enregistrés dans la base de données.

- **Liste des utilisateurs** - `/users`: La liste des différents utilisateurs est retournée. Cliquer sur l'un des utilisateurs de la liste envoi vers la page d'historique de conversation pour cet utilisateur.

- **Historiques de conversations** - `userConversation?username="user"` : Retourne l'historique de conversation pour l'utilisateur choisi, ici `user`.
  
## Démarrage de l'application

### 1. Configuration des variables d'environnement
Assurez-vous que les variables suivantes sont correctement configurées dans vos fichiers `application.properties` pour `motivational-api` & `motivational-chat`:

Pour `motivational-api`:

```bash
spring.application.name=motivational-api

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/motivational_database
spring.datasource.username=votre_utilisateur_mysql
spring.datasource.password=votre_mot_de_passe_mysql
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

server.port=8081
```

Pour `motivational-chat`:

```bash
spring.application.name=motivational-chat

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/motivational_database
spring.datasource.username=votre_utilisateur_mysql
spring.datasource.password=votre_mot_de_passe_mysql
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### 2. Lancement

- L'API `motivational-api` est accessible via: `http://localhost:8081/getQuote`
- Le chat `motivational-chat` est accessible via: `http://localhost:8080/conversation`


