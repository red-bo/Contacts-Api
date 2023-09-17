# Contacts-Api
View of contacts ressources


Pour lancer l'API

Il faut lancer un bdd postgresql, pour cela vous pouvez utiliser  la commande suivante si vous avez docker 

docker run --name contact-bdd -e POSTGRES_PASSWORD=password -e POSTGRES_DB=contact -p 5432:5432 -d postgres

le fichier application.properties est configuré de tel sorte à généré automatiquement les tables et les cologne.

le fichier data.sql insert un utilisateur red qui est ADMIN. Si vous voulez vous enregistrer vous le pouvez;

Les end-points /api/auth/** sont tous ouvert.

Une fois votre application lancé vous pouvez accéder à swagger ui via cette url : http://localhost:8080

Vous pourrez vous authentifier avec l'url api/auth/login avec l'utilisateur red :

username : red
password : bo

L'excution du end-point donnera en reponse un token, par exemple :

"access_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZWQiLCJpYXQiOjE2OTQ5OTEyMzQsImV4cCI6MTY5NDk5MjEzNH0.m_XZCPtdWh3Qj1bJCGLYsAQaiBjcbYsqB5EOB8pHPz4"

Vous pouvez ensuite copier le token : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZWQiLCJpYXQiOjE2OTQ5OTEyMzQsImV4cCI6MTY5NDk5MjEzNH0.m_XZCPtdWh3Qj1bJCGLYsAQaiBjcbYsqB5EOB8pHPz4
et vous authentifier sur swagger via le bouton "Authorize "en haut à droite.

En cliquant dessus une pop-up s'ouvre, vous pouvez coller le token dans l'invite et cliquer sur le bouton "Authorize"
de la pop-ip. Puis cliquer sur le bouton "Close" pour revenir à swagger ui.

Une fois authentifié, toutes les autres urls seront disponible et vous pourrez jouer avec l'api.