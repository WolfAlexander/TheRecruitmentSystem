#!/usr/bin/env bash
#Setting enviroment variables - needed for maven docker plugin if running docker toolbox
echo Setting enviroment variables
export DOCKER_HOST=https://192.168.99.100:2376
export DOCKER_CERT_PATH=C:/Users/Alexander/.docker/machine/machines/default

echo
echo
echo

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

echo
echo
echo