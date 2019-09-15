FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_PATH
COPY ${JAR_PATH} app.jar
COPY wait-for .
RUN chmod +x /wait-for