#!/usr/bin/env bash
#Setting enviroment variables - needed for maven docker plugin if running docker toolbox
echo Setting enviroment variables
export DOCKER_HOST=https://192.168.99.100:2376
export DOCKER_CERT_PATH=C:/Users/Alexander/.docker/machine/machines/default

echo
echo
echo

./build-services-docker-unix.sh