FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/rest-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} rest-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","rest-api.jar"]