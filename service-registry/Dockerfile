FROM openjdk:17.0.1-jdk-slim
MAINTAINER damien.tschan@cddf.ch
COPY target/service-registry.jar service-registry.jar
ENTRYPOINT ["java","-jar","/service-registry.jar"]