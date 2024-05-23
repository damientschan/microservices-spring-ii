FROM openjdk:17.0.1-jdk-slim
MAINTAINER damien.tschan@cddf.ch
COPY target/api-gateway.jar api-gateway.jar
ENTRYPOINT ["java","-jar","/api-gateway.jar"]