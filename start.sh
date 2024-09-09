#!/bin/bash

# Run the package script to build the Spring Boot application
./docker/package_app.sh

# Stop the running containers
docker compose  -f ./docker/docker-compose.yml down

# Start the containers again
docker compose  -f ./docker/docker-compose.yml up