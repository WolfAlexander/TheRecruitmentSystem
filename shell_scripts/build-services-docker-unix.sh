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

#Building discoveryservice with maven docker plugin
echo Building discoveryservice
cd ..
cd discoveryservice
mvn package docker:build -Ddocker.host=$DOCKER_HOST -Ddocker.cert.path=$DOCKER_CERT_PATH

echo
echo
echo

#Building edgeservice with maven docker plugin
echo Building edgeservice
cd ..
cd edgeservice
mvn package docker:build -Ddocker.host=$DOCKER_HOST -Ddocker.cert.path=$DOCKER_CERT_PATH

#Building registration-service with maven docker plugin
echo Building registration service
cd ..
cd registration-service
mvn package docker:build -Ddocker.host=$DOCKER_HOST -Ddocker.cert.path=$DOCKER_CERT_PATH