#!/bin/bash

# Hacemos el build de cada uno de los microservicios

echo "Building Docker images for microservices..."

cd bike-data
docker build -t javiimiralles/bike-data:v1.0 .
cd ..

cd bike-service
docker build -t javiimiralles/bike-service:v1.3 .
cd ..

cd city-service
docker build -t javiimiralles/city-service:v1.2 .
cd ..

cd config-server
docker build -t javiimiralles/config-server:v1.3 .
cd ..

cd pollution-data
docker build -t javiimiralles/pollution-data:v1.0 .
cd ..

cd pollution-service
docker build -t javiimiralles/pollution-service:v1.2 .
cd ..

cd schedulers
docker build -t javiimiralles/schedulers:v1.2 .
cd ..

echo "Docker images built successfully."

# Login a Docker Hub
docker login

echo "Pushing Docker images to Docker Hub..."

# Subimos las imágenes a Docker Hub
docker push javiimiralles/bike-data:v1.0
docker push javiimiralles/bike-service:v1.3
docker push javiimiralles/city-service:v1.2
docker push javiimiralles/config-server:v1.3
docker push javiimiralles/pollution-data:v1.0
docker push javiimiralles/pollution-service:v1.2
docker push javiimiralles/schedulers:v1.2

echo "Docker images pushed to Docker Hub successfully."