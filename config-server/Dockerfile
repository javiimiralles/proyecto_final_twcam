FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/*.jar app.jar

COPY src/main/resources/config /config

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "app.jar"]