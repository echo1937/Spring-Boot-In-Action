FROM adoptopenjdk:11-jre-hotspot
MAINTAINER echo1937 <echo1937@example.com>

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]

ARG JAR_FILE
ADD target/${JAR_FILE} app.jar

