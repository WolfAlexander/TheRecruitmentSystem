#!/usr/bin/env bash
cd ..
#docker-compose up -d

echo Startup will take around 20-30 seconds

docker run -p "9090:9090" -d discovery-service
docker run -p "8080:8080" -d edge-service
docker run -p "8888:8888" -d registration-service
