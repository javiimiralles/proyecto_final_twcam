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