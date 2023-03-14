#!/bin/sh

# TODO Run container on env/prod/docker-compose

GROUP=group-happynewyear
SERVICE=studynight-backend
TAG=latest

echo "$GITHUB_TOKEN" | docker login ghcr.io -u "$GITHUB_USERNAME" --password-stdin
docker pull ghcr.io/"$GROUP"/"$SERVICE":"$TAG"
