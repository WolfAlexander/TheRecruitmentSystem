#!/usr/bin/env bash
#Building configservice with maven docker plugin
echo Building configservice
cd ..
cd configservice
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

echo
echo
echo

#Building authentication-service with maven docker plugin
echo Building authentication service
cd ..
cd authservice
mvn package docker:build -Ddocker.host=$DOCKER_HOST -Ddocker.cert.path=$DOCKER_CERT_PATH

echo
echo
echo

#Building registration-service with maven docker plugin
echo Building registration service
cd ..
cd registration-service
mvn package docker:build -Ddocker.host=$DOCKER_HOST -Ddocker.cert.path=$DOCKER_CERT_PATH

#Building jobapplication-service with maven docker plugin
echo Building jobapplication service
cd ..
cd jobapplicationservice
mvn package docker:build -Ddocker.host=$DOCKER_HOST -Ddocker.cert.path=$DOCKER_CERT_PATH