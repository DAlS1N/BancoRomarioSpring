FROM openjdk:22-jdk-slim

WORKDIR /app

# Copie o arquivo manualmente, verificando se ele está disponível
ARG JAR_FILE=target/banco-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 9090
