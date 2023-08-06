#!/bin/sh

TAG=$1
ENV_DIR=$2

OLD="blue"
NEW="green"
PORT=8081
GREEN_YML=$ENV_DIR/docker-compose.green.yml
if docker-compose -p green -f "$GREEN_YML" ps | grep -q Up; then
  OLD="green"
  NEW="blue"
  PORT=8080
fi
OLD_YML=$ENV_DIR/docker-compose.$OLD.yml
NEW_YML=$ENV_DIR/docker-compose.$NEW.yml

export TAG="$TAG"
docker-compose -p "$NEW" -f "$NEW_YML" up -d
sleep 10

SEQ=$(seq 1 3)
for COUNT in $SEQ; do
  if docker-compose -p "$NEW" -f "$NEW_YML" ps | grep -q Up; then
    # TODO /etc/nginx/nginx.conf include /etc/nginx/conf.d/service-url.inc;
    echo "set \$service_url http://127.0.0.1:${PORT};" >/etc/nginx/conf.d/service-url.inc
    nginx -s reload
    docker-compose -p "$OLD" -f "$OLD_YML" down
    break
  fi
  if [ "$COUNT" -eq 3 ]; then
    exit 1
  fi
  sleep 10
done
