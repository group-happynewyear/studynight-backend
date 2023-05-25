#!/bin/sh

TAG=$1

GROUP=group-happynewyear
SERVICE=studynight-backend
IMG=ghcr.io/"$GROUP"/"$SERVICE":"$TAG"

echo "$GITHUB_TOKEN" | docker login ghcr.io -u "$GITHUB_USERNAME" --password-stdin

docker rmi "$IMG"
docker pull "$IMG"
