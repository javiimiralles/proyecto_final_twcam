# Cómo configurar y arrancar el proyecto

## 📁 Bases de datos
El siguiente script crea dos contenedores (uno MySQL y otro de MongoDB) y crea las bases de datos necesarias para el proyecto:

```
./setup_databases.sh
```

## 🔒 Keycloak
1. <strong>Crear el contenedor de Keycloak</strong>
```
docker run -d \
  --name keycloak \
  -p 8089:8080 \
  -e KEYCLOAK_ADMIN=admin \
  -e KEYCLOAK_ADMIN_PASSWORD=admin \
  quay.io/keycloak/keycloak:26.2.4 \
  start-dev
```

2. Entrar a ``http://localhost:8089``. Las credenciales son:
    - Usuario: admin
    - Contraseña: admin

3. En Manage realms, darle a Create realm y cargar el archivo ``realm-export.json`` que proporcionamos.

## 🔒 Generar los tokens
1. En Keycloak, entrar a Clients, y para los clientes admin-client, servicio-client, aparcamiento-client y estacion-client ir a Credentials y regenerar el Client Secret, ya que al importar la configuración el secreto no se importa.

2. Para obtener el token de cada uno de los clientes que hemos mencionado se debe ejecutar:
```
curl -X POST "http://localhost:8089/realms/final-twcam/protocol/openid-connect/token" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "client_id={nombre del cliente}" \
  -d "client_secret={secreto del cliente}" \
  -d "grant_type=client_credentials"
```

Se recomienda obetener el token del servicio-client y ponerlo en el archivo ``.env`` antes de continuar.

## 💻 Compilar los proyectos
Para dejar los proyectos listos para ser ejecutados se debe usar el script:
```
./compile_mvn.sh
```

## 🚀 Ejecutar los proyectos
Los proyectos deben ejecutarse en el siguiente orden y con los siguientes perfiles:

1. ``config-server`` (sin perfil)
2. ``bike-service``, ``pollution-service``, ``city-service`` (con perfil ``dev``)
3. ``schedulers`` (con perfil ``dev``)

Antes de arrancar ``schedulers`` se debe generar un token para el cliente servicio-client y ponerlo en el archivo ``.env``.

Al arrancar ``schedulers`` comenzará a hacer peticiones a ``city-service`` cada 60 segundos para agregar los datos. Este tiempo se puede modificar en el archivo ``schedulers.propperties`` que hay en la carpeta ``resources -> config`` del ``config-server``.

## 📎Probar los endpoints
Para probar los endpoints se puede hacer uso de OpenAPI, cuyo acceso se especifica en el siguiente punto. 

A aquellos endpoints que necesite un rol específico para ser ejecutados se deberá poner su token correspondiente en el Bearer.

## 📄 OpenAPI
Con los servicios arrancados, para acceder a la documentación de cada API entrar a la url ``http://localhost:{puerto}/api/v1/swagger-ui/index.html`` y en el buscador poner ``/api/v1/api-spec``.

Puertos disponibles:
- ``bike-service``: 8080
- ``pollution-service``: 8081
- ``city-service``: 8082
