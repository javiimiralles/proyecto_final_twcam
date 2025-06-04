# Cómo configurar y arrancar el proyecto

## 📁 Bases de datos
El siguiente script crea dos contenedores (uno MySQL y otro de MongoDB) y crea las bases de datos necesarias para el proyecto:

```
./setup_databases.sh
```

## 🔒 Keycloak
<strong>ToDo - Poner como configurar keycloak</strong>

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

Al arrancar ``schedulers`` comenzará a hacer peticiones a ``city-service`` cada 60 segundos para agregar los datos. Este tiempo se puede modificar en el archivo ``schedulers.propperties`` que hay en la carpeta ``resources -> config`` del ``config-server``.

## 📄 OpenAPI
Con los servicios arrancados, para acceder a la documentación de cada API entrar a la url ``http://localhost:{puerto}/api/v1/swagger-ui/index.html`` y en el buscador poner ``/api/v1/api-spec``.

Puertos disponibles:
- ``bike-service``: 8080
- ``pollution-service``: 8081
- ``city-service``: 8082

## 📎Llamadas a los endpoints
La tabla con todos los endpoints se encuentra en la memoria. Los tokens de acceso para cada uno de los roles se deben poner en el Bearer al hacer la petición. Son los siguientes:

PONER LOS TOKENS