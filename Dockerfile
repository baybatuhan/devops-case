FROM maven:3.8-openjdk-alpine AS builder

WORKDIR /app

COPY pom.xml ./
COPY KafkaProducerExample.java ./

RUN mvn package

FROM openjdk:17-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar ./

EXPOSE 8080

CMD ["java", "-jar", "kafka-producer-1.0-SNAPSHOT.jar"]


