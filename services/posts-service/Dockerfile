FROM openjdk:17.0.1-jdk-slim
MAINTAINER damien.tschan@cddf.ch
COPY target/posts-service.jar posts-service.jar
ENTRYPOINT ["java","-jar","/posts-service.jar"]