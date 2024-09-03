#!/bin/bash

# Установите значения для переменных окружения
export POSTGRES_HOST=db
export POSTGRES_USER=myusername
export POSTGRES_PASSWORD=mypassword
export POSTGRES_DB=mydatabase

# Run the package script to build the Spring Boot application
./package.sh

# Запустите Docker Compose
# Stop the running containers
docker-compose down

# Remove the volumes
docker volume rm $(docker volume ls -q)

# Start the containers again
docker-compose up