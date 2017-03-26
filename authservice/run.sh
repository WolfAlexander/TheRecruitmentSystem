#!/usr/bin/env bash


# Stop container if running now
echo Stoping running container
docker stop auth-service

# Remove old container
echo Removing old container
docker rm auth-service

# Start new container from an existing image
echo Starting new container
docker run -d --name auth-service -p "9898:9898" -e "SPRING_PROFILES_ACTIVE=production" -e "SPRING_CONFIG_LOCATION=classpath:security/auth_secrets.properties" iv1201/auth-service