FROM openjdk:17.0.1-jdk-slim
MAINTAINER seb.chevre@gmail.com
COPY target/service-registry.jar service-registry.jar
ENTRYPOINT ["java","-jar","/service-registry.jar"]