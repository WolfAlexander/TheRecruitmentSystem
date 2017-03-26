#!/usr/bin/env bash
cd ..
#docker-compose up -d

echo Startup will take around 20-30 seconds
echo
echo

#Run config-service
echo Starting Configuration Service
./configservice/run.sh
sleep 15

echo
echo

#Run discovery-service
echo Starting Discovery Service
./discoveryservice/run.sh

echo
echo

#Run edge-service
echo Starting Edge Service
./edgeservice/run.sh

echo
echo

#Run authentication-service
echo Starting Authorization Service
./authservice/run.sh

echo
echo

#Run registration-service
echo Starting Registration Service
./registration-service/run.sh

echo
echo

#Run jobapplication-service
./jobapplicationservice/run.sh
