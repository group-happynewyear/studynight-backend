#!/bin/sh

ENV_DIR=$1
REPOSITORY_DIR=$ENV_DIR/studynight-properties

rm -rf "$REPOSITORY_DIR"
git clone git@github.com:group-happynewyear/studynight-properties.git "$REPOSITORY_DIR"
