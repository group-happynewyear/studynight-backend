#!/bin/sh

TAG=latest

SCR="../.script"
sh "$SCR"/pull-docker-image.sh "$TAG"
sh "$SCR"/clone-property-repository.sh "$(pwd)"

docker-compose up -d
