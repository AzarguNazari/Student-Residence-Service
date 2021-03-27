#!/bin/bash

./mvnw spring-boot:build-image -pl backend/Api-Gateway/pom.xml
./mvnw spring-boot:build-image -pl backend/Appliance-Service/pom.xml
./mvnw spring-boot:build-image -pl backend/Authorization-Server/pom.xml
./mvnw spring-boot:build-image -pl backend/Bulletinboard-Service/pom.xml
./mvnw spring-boot:build-image -pl backend/Spring-Boot-Admin/pom.xml

docker-compose -f config/docker-compose.yml up -d
docker-compose -f backend/docker-compose-application.yml up -d
docker-compose -f frontend/docker-compose.yml up -d
