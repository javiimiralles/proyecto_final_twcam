#!/bin/bash

# Hacemos el build de cada uno de los microservicios

echo "Building Docker images for microservices..."

cd bike-data
docker build -t javiimiralles/bike-data:v1.2 .
cd ..

cd bike-service
docker build -t javiimiralles/bike-service:v1.6 .
cd ..

cd city-service
docker build -t javiimiralles/city-service:v1.3 .
cd ..

cd config-server
docker build -t javiimiralles/config-server:v1.5 .
cd ..

cd pollution-data
docker build -t javiimiralles/pollution-data:v1.1 .
cd ..

cd pollution-service
docker build -t javiimiralles/pollution-service:v1.3 .
cd ..

cd schedulers
docker build -t javiimiralles/schedulers:v1.3 .
cd ..

echo "Docker images built successfully."

# Login a Docker Hub
docker login

echo "Pushing Docker images to Docker Hub..."

# Subimos las imágenes a Docker Hub
docker push javiimiralles/bike-data:v1.2
docker push javiimiralles/bike-service:v1.6
docker push javiimiralles/city-service:v1.3
docker push javiimiralles/config-server:v1.5
docker push javiimiralles/pollution-data:v1.1
docker push javiimiralles/pollution-service:v1.3
docker push javiimiralles/schedulers:v1.3

echo "Docker images pushed to Docker Hub successfully."