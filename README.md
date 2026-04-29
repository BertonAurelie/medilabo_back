# Medilabo-starter-pack

Docker compose bug avec un version trop recente (ne prend pas en charge les path HTTP avec bake)
Downgrade en version 2.30.3  qui est une version sans bake:

mkdir "$env:USERPROFILE\.docker\cli-plugins" -Force
 
Invoke-WebRequest `
 -Uri "https://github.com/docker/compose/releases/download/v2.30.3/docker-compose-windows-x86_64.exe" `
 -OutFile "$env:USERPROFILE\.docker\cli-plugins\docker-compose.exe"
 
-----

Pour build et lancer le projet:

docker compose build

docker compose up

Url du projet: http://127.0.0.1/

------
 
Pour remove le docker compose precedement installé:

Remove-Item "$env:USERPROFILE\.docker\cli-plugins\docker-compose.exe"


