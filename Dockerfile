FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

  # Primero copiar pom.xml y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

  # Luego copiar el código fuente
COPY src src

  # Finalmente compilar
RUN mvn package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/quarkus-app/ /app/
EXPOSE 8080
CMD ["java", "-jar", "quarkus-run.jar"]
