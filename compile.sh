#!/bin/bash

cd shared
mvn clean install
cd ..

cd config-server
mvn clean package -DskipTests
cd ..

cd bike-service
mvn clean package -DskipTests
cd ..

cd pollution-service
mvn clean package -DskipTests
cd ..

cd city-service
mvn clean package -DskipTests
cd ..

cd schedulers
mvn clean package -DskipTests
cd ..