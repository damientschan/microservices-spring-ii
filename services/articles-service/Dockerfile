FROM openjdk:17.0.1-jdk-slim
MAINTAINER damien.tschan@cddf.ch
COPY target/articles-service.jar articles-service.jar
ENTRYPOINT ["java","-jar","/articles-service.jar"]