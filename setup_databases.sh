#!/bin/bash

# 1️⃣ Levantar contenedor MySQL
docker run -d \
    --name mysql-container \
    -e MYSQL_ROOT_PASSWORD=root \
    -e MYSQL_DATABASE=root \
    -p 3307:3306 \
    mysql:latest

echo "⏳ Esperando a que MySQL se inicie..."
sleep 20

# 2️⃣ Crear bases de datos en MySQL
docker exec -i mysql-container mysql -uroot -proot <<EOF
CREATE DATABASE IF NOT EXISTS bicicletas_sql;
CREATE DATABASE IF NOT EXISTS polucion_sql;
EOF

echo "✅ Bases de datos MySQL creadas: bicicletas_sql, polucion_sql"

# 3️⃣ Levantar contenedor MongoDB
docker run -d \
    --name mongodb-container \
    -p 27017:27017 \
    mongo:latest

echo "⏳ Esperando a que MongoDB se inicie..."
sleep 10

# 4️⃣ Crear bases de datos en MongoDB (se crean automáticamente al insertar datos)
docker exec -i mongodb-container mongosh <<EOF
use bicicletas_nosql
db.dummy.insertOne({msg: "base creada"})

use polucion_nosql
db.dummy.insertOne({msg: "base creada"})

use ayuntamiento_nosql
db.dummy.insertOne({msg: "base creada"})
EOF

echo "✅ Bases de datos MongoDB creadas: bicicletas_nosql, polucion_nosql, ayuntamiento_nosql"
