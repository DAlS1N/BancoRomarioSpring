FROM openjdk:22
COPY target/demo-0.0.1-SHAPSHOT.jar app.jar
ENTRYPOINT["java","-jar","/app.jar"]