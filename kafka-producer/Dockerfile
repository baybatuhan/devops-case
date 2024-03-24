FROM openjdk:17-jdk-alpine

RUN apk --no-cache add maven curl tar bash

WORKDIR /app

COPY pom.xml ./

RUN mvn dependency:go-offline

RUN mvn clean install

COPY ./target/kafka-producer.jar ./

COPY producer.java ./

RUN mvn clean install

CMD ["java", "-jar", "kafka-producer-*.jar"]

ENV KAFKA_BROKERS="localhost:9092"
