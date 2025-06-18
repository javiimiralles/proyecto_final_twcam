#!/bin/bash

set -e  # salir si algún comando falla

NAMESPACE=proyecto-final

#### Creacion del namespace
echo "🧱 Creando el namespace $NAMESPACE..."
kubectl get namespace $NAMESPACE || kubectl create namespace $NAMESPACE

#### Despliegue de las bases de datos
echo "📦 Desplegando las bases de datos..."
echo "📦 MongoDB..."
kubectl apply -f mongodb/ayuntamiento/ -n $NAMESPACE
kubectl apply -f mongodb/bicicletas/ -n $NAMESPACE
kubectl apply -f mongodb/polucion/ -n $NAMESPACE

echo "📦 MySQL..."
kubectl apply -f mysql/bicicletas/ -n $NAMESPACE
kubectl apply -f mysql/polucion/ -n $NAMESPACE

echo "⏳ Esperando a que las bases de datos estén listas..."
kubectl wait --for=condition=ready pod -l app=mongo -n $NAMESPACE --timeout=120s
kubectl wait --for=condition=ready pod -l app=mysql -n $NAMESPACE --timeout=120s

#### Despliegue del config-server
echo "🧩 Desplegando el config-server..."
kubectl apply -f config-server/ -n $NAMESPACE

echo "⏳ Esperando a que el config-server esté listo..."
kubectl wait --for=condition=ready pod -l app=config-server -n $NAMESPACE --timeout=120s

echo "🕓 Esperando unos segundos extra para asegurar disponibilidad..."
sleep 5

#### Despliegue de Keycloak
echo "🔐 Desplegando Keycloak..."
kubectl apply -f keycloak/ -n $NAMESPACE

echo "⏳ Esperando a que Keycloak esté listo..."
kubectl wait --for=condition=ready pod -l app=keycloak -n $NAMESPACE --timeout=120s

#### Despliegue de las capas de datos
echo "📊 Desplegando las capas de datos..."
kubectl apply -f bicicletas/data/ -n $NAMESPACE
kubectl apply -f polucion/data/ -n $NAMESPACE

echo "⏳ Esperando a que las capas de datos estén listas..."
kubectl wait --for=condition=ready pod -l app=bike-data -n $NAMESPACE --timeout=120s
kubectl wait --for=condition=ready pod -l app=pollution-data -n $NAMESPACE --timeout=120s

#### Despliegue de las APIs
echo "🚀 Desplegando las APIs..."
kubectl apply -f ayuntamiento/api/ -n $NAMESPACE
kubectl apply -f bicicletas/api/ -n $NAMESPACE
kubectl apply -f polucion/api/ -n $NAMESPACE

echo "⏳ Esperando a que las APIs estén listas..."
kubectl wait --for=condition=ready pod -l app=city-service -n $NAMESPACE --timeout=120s
kubectl wait --for=condition=ready pod -l app=bike-service -n $NAMESPACE --timeout=120s
kubectl wait --for=condition=ready pod -l app=pollution-service -n $NAMESPACE --timeout=120s

#### Despliegue de los ingress
echo "🌐 Desplegando los ingress..."
kubectl apply -f ingress/ -n $NAMESPACE

echo "✅ Se ha completado el despliegue en el namespace $NAMESPACE."



