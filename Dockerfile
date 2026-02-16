# Multi-stage build para Consumer Location
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar archivos y descargar dependencias incluyendo Oracle Security
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de producción
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiar el JAR compilado
COPY --from=build /app/target/*.jar app.jar

# Copiar el wallet de Oracle
COPY Wallet/ /app/wallet/

# Exponer puerto
EXPOSE 8083

# Usuario no root
RUN groupadd -r spring && useradd -r -g spring spring
RUN chown -R spring:spring /app/wallet
USER spring:spring

# Ejecutar aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
