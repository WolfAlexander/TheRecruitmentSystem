#!/bin/bash

#Build project
mvn package

#Build docker image
docker build  -t iv1201/jobapplication:latest .

#docker (remove old)
docker stop jobapplicationservice
docker rm jobapplicationservice

#docker (create new)
docker run -d --name jobapplicationservice -p 8080:8080 iv1201/jobapplication
