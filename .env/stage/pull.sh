#!/bin/sh

GROUP=group-happynewyear
SERVICE=studynight-backend
TAG=latest

IMG=ghcr.io/"$GROUP"/"$SERVICE":"$TAG"

docker rmi $IMG
docker pull $IMG
