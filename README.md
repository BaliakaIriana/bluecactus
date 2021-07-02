# Bluecactusfactory TEST Backend
Ceci est la partie backend du test

## Installation et lancement
Vous devez avoir installé:
 - Java SDK 8+
 - MySQL 

Cloner le projet puis dans le dossier racine du projet lancer la commande afin de compiler un fichier .jar executable:

```bash
./gradlew build
```

Ensuite lancer l'une des commandes suivantes pour lancer le programme backend:

```bash
./gradlew run
```
ou
```bash
java -jar ./build/libs/bluecactus-0.0.1-SNAPSHOT.jar
```
Normalement vous pouvez tester en utilisant les requêtes HTTP:

- pour la connexion
```http request
POST http://localhost:8080/api/public/signin
Content-Type: application/json

{
"username": "baliaka@gmail.com",
"password": "baliaka"
}

```
 - pour la liste des employes

```http request
GET http://localhost:8080/api/employes
Authorization: Bearer ${token obtenu apres connexion dans le header 'Authorization'}
```