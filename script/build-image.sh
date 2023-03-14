#!/bin/sh

# Build image for env/local/docker-compose

SERVICE=studynight-backend

REPOSITORY_ROOT="$HAPPYNEWYEAR_HOME"/"$SERVICE"
cd "$REPOSITORY_ROOT" || exit

./gradlew clean build

docker rmi studynight-backend
docker build -t studynight-backend .
