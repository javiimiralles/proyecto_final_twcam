#!/bin/bash

set -e  # salir si algún comando falla

NAMESPACE=proyecto-final

echo "🧹 Eliminando recursos del namespace $NAMESPACE..."

### Eliminar Ingress
echo "🌐 Eliminando ingress..."
kubectl delete -f ingress/ -n $NAMESPACE || true

### Eliminar APIs
echo "🚀 Eliminando APIs..."
kubectl delete -f ayuntamiento/api/ -n $NAMESPACE || true
kubectl delete -f bicicletas/api/ -n $NAMESPACE || true
kubectl delete -f polucion/api/ -n $NAMESPACE || true

### Eliminar capas de datos
echo "📊 Eliminando capas de datos..."
kubectl delete -f bicicletas/data/ -n $NAMESPACE || true
kubectl delete -f polucion/data/ -n $NAMESPACE || true

### Eliminar Keycloak
echo "🔐 Eliminando Keycloak..."
kubectl delete -f keycloak/ -n $NAMESPACE || true

### Eliminar config-server
echo "🧩 Eliminando config-server..."
kubectl delete -f config-server/ -n $NAMESPACE || true

### Eliminar bases de datos
echo "📦 Eliminando MySQL..."
kubectl delete -f mysql/bicicletas/ -n $NAMESPACE || true
kubectl delete -f mysql/polucion/ -n $NAMESPACE || true

echo "📦 Eliminando MongoDB..."
kubectl delete -f mongodb/ayuntamiento/ -n $NAMESPACE || true
kubectl delete -f mongodb/bicicletas/ -n $NAMESPACE || true
kubectl delete -f mongodb/polucion/ -n $NAMESPACE || true

### Eliminar ConfigMaps y Secrets
echo "📄 Eliminando configmaps y secrets..."
kubectl delete configmap --all -n $NAMESPACE || true
kubectl delete secret --all -n $NAMESPACE || true

### Eliminar el namespace completo
echo "🗑 Eliminando namespace $NAMESPACE..."
kubectl delete namespace $NAMESPACE || true

echo "✅ Todo ha sido eliminado correctamente."