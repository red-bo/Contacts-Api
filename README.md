# Contacts-Api
View of contacts ressources


Pour lancer l'API

Il faut lancer une base de données PostgreSQL. Pour cela, vous pouvez utiliser la commande suivante si vous avez Docker :

docker run --name contact-bdd -e POSTGRES_PASSWORD=password -e POSTGRES_DB=contact -p 5432:5432 -d postgres

Le fichier application.properties est configuré de telle sorte à générer automatiquement les tables et les colonnes.

Le fichier data.sql insère un utilisateur "red" qui est ADMIN. Si vous voulez vous enregistrer, vous le pouvez ;

Les endpoints /api/auth/** sont tous ouverts.

Une fois votre application lancée, vous pouvez accéder à Swagger UI via cette URL : http://localhost:8080

Vous pourrez vous authentifier avec l'URL api/auth/login avec l'utilisateur "red" :

Nom d'utilisateur : red
Mot de passe : bo

L'exécution de l'endpoint donnera en réponse un token, par exemple :

"access_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZWQiLCJpYXQiOjE2OTQ5OTEyMzQsImV4cCI6MTY5NDk5MjEzNH0.m_XZCPtdWh3Qj1bJCGLYsAQaiBjcbYsqB5EOB8pHPz4"

Vous pouvez ensuite copier le token : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZWQiLCJpYXQiOjE2OTQ5OTEyMzQsImV4cCI6MTY5NDk5MjEzNH0.m_XZCPtdWh3Qj1bJCGLYsAQaiBjcbYsqB5EOB8pHPz4 et vous authentifier sur Swagger via le bouton "Authorize" en haut à droite.

En cliquant dessus, une fenêtre pop-up s'ouvre, vous pouvez coller le token dans l'invite et cliquer sur le bouton "Authorize" de la pop-up. Ensuite, cliquez sur le bouton "Close" pour revenir à Swagger UI.

Une fois authentifié, toutes les autres URLs seront disponibles et vous pourrez interagir avec l'API."
