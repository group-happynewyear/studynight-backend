#!/bin/sh

SERVICE=studynight-backend
REPOSITORY_ROOT="$HAPPYNEWYEAR_HOME"/"$SERVICE"

docker rmi "$SERVICE"

cd "$REPOSITORY_ROOT" || exit

./gradlew clean build -x test
docker build -t "$SERVICE" .
