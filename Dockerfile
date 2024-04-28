FROM openjdk:21

WORKDIR /api

COPY ./target/api-0.0.1-SNAPSHOT.jar .

ENTRYPOINT java -jar api-0.0.1-SNAPSHOT.jar