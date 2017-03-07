#!/usr/bin/env bash

# Stop container if running now
echo Stoping running container
docker stop config-service

# Remove old container
echo Removing old container
docker rm config-service

# Start new container from an existing image
echo Starting new container
docker run -d --name config-service -p "9999:9999" iv1201/config-service