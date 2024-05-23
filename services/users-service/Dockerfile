FROM openjdk:17.0.1-jdk-slim
MAINTAINER damien.tschan@cddf.ch
COPY target/users-service.jar users-service.jar
ENTRYPOINT ["java","-jar","/users-service.jar"]