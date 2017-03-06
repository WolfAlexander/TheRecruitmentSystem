#!/usr/bin/env bash

# Stop container if running now
echo Stoping running container
docker stop edge-service

# Remove old container
echo Removing old container
docker rm edge-service

# Start new container from an existing image
echo Starting new container
docker run --net="host" -d --name edge-service -p "8080:8080" iv1201/edge-service