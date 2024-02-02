FROM openjdk:21-jdk-slim

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=target/gpaStudent-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]