#!/usr/bin/env bash

# Stop container if running now
echo Stoping running container
docker stop jobapplication-service

# Remove old container
echo Removing old container
docker rm jobapplication-service

# Start new container from an existing image
echo Starting new container
docker run -d --name jobapplication-service -p "8585:8585" iv1201/jobapplication-service