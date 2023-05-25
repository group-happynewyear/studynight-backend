#!/bin/sh

TAG=latest

SCR="../.script"
sh "$SCR"/pull-docker-image.sh "$TAG"
sh "$SCR"/clone-property-repository.sh "$(pwd)"
sh "$SCR"/blue-green.sh "$TAG" "$(pwd)"
