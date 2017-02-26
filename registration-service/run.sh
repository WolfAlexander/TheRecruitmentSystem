#!/usr/bin/env bash

# Stop container if running now
echo Stoping running container
docker stop registration-service

# Remove old container
echo Removing old container
docker rm registration-service

# Start new container from an existing image
echo Starting new container
docker run -d --name registration-service -p "8888:8888" iv1201/registration-service