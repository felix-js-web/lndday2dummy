#!/bin/bash

# Build the Spring Boot application using Gradle
./gradlew clean build

 docker build -f ./docker/Dockerfile -t lnd-web-api2 .