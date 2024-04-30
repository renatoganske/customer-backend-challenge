FROM azul/zulu-openjdk-alpine:21
WORKDIR /api
COPY ./target/api-0.0.1-SNAPSHOT.jar /api
EXPOSE 8081
CMD [ "java", "-jar", "api-0.0.1-SNAPSHOT.jar"]
