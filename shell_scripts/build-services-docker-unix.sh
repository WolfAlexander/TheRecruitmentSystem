#!/usr/bin/env bash
#Building configservice with maven docker plugin
echo Building configservice
cd ..
cd configservice
mvn package docker:build -Ddocker.host=$DOCKER_HOST -Ddocker.cert.path=$DOCKER_CERT_PATH

echo
echo
echo

#Building configservice with maven docker plugin
echo Building discoveryservice
cd ..
cd discoveryservice
mvn package docker:build -Ddocker.host=$DOCKER_HOST -Ddocker.cert.path=$DOCKER_CERT_PATH

echo
echo
echo

#Going to discoveryservice folder
echo Building discoveryservice
cd ..
cd discoveryservice
mvn package docker:build -Ddocker.host=$DOCKER_HOST -Ddocker.cert.path=$DOCKER_CERT_PATH

echo
echo
echo

#Going to edgeservice folder
echo Building edgeservice
cd ..
cd edgeservice
mvn package docker:build -Ddocker.host=$DOCKER_HOST -Ddocker.cert.path=$DOCKER_CERT_PATH

#Going to resgistration-service

echo
echo
echo