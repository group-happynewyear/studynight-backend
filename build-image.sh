#!/bin/sh

./gradlew clean build

docker rmi studynight-backend
docker build -t studynight-backend .
