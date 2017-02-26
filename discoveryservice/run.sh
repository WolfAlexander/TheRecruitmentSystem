#!/usr/bin/env bash

# Stop container if running now
echo Stoping running container
docker stop discovery-service

# Remove old container
echo Removing old container
docker rm discovery-service

# Start new container from an existing image
echo Starting new container
docker run -d --name discovery-service -p "9090:9090" iv1201/discovery-service