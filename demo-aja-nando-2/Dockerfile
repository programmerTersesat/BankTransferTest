FROM openjdk:17-jdk-alpine
WORKDIR /opt/app
COPY target/demo-aja-nando-0.0.1-SNAPSHOT.jar demodocker.jar
EXPOSE 8080
ENTRYPOINT exec java -jar demodocker.jar