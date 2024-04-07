FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install amazoncorretto:21.0.1 -y
COPY . .
RUN ./gradlew bootJar --no-daemon

# Set the base image. Linux installation. If version is not specified it uses the latest
FROM amazoncorretto:21.0.1
MAINTAINER Eddy
# Copy files between the host and the container. Copy the file in build->libs and rename it to app.jar
COPY build/libs/shopIT.jar app.jar
EXPOSE 8089
# Allows configuration of container that will run as a executable. When it started run the java, -jar and /app.jar
#       ["Executble", "param1", "param2"]
ENTRYPOINT ["java", "-jar", "/app.jar"]