FROM openjdk:17-jdk-alpine

RUN apk --no-cache add maven curl tar bash

WORKDIR /app

COPY pom.xml ./

RUN mvn dependency:go-offline

RUN mvn clean install

COPY kafka-consumer-*.jar ./

COPY consumer.java ./

RUN mvn clean install

CMD ["java", "-jar", "kafka-consumer-*.jar"]

ENV KAFKA_BROKERS="localhost:9092"

EXPOSE 9092
