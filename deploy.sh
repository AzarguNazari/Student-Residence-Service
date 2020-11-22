#!/bin/bash

./mvnw clean package -pl backend/Api-Gateway/pom.xml
./mvnw clean package -pl backend/Appliance-Service/pom.xml
./mvnw clean package -pl backend/Authorization-Server/pom.xml
./mvnw clean package -pl backend/Bulletinboard-Service/pom.xml
./mvnw clean package -pl backend/springBoot-monitor/pom.xml

docker-compose -f backend/docker-compose-application.yml up -d
docker-compose -f frontend/docker-compose.yml up -d

