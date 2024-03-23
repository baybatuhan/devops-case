FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY kafka-producer-consumer/kafka-producer/pom.xml ./

RUN mvn -f kafka-producer-consumer/kafka-producer/pom.xml clean install

COPY kafka-producer-consumer/kafka-producer/target/kafka-producer-*.jar ./

COPY kafka-producer-consumer/kafka-consumer/pom.xml ./

RUN mvn -f kafka-producer-consumer/kafka-consumer/pom.xml clean install

COPY kafka-producer-consumer/kafka-producer/target/kafka-consumer-*.jar ./

COPY kafka-producer-consumer/kafka-producer/src/main/java/producer.java ./

COPY kafka-producer-consumer/kafka-consumer/src/main/java/consumer.java ./

CMD ["java", "-jar", "kafka-producer-*.jar", "kafka-consumer-*.jar"]

ENV KAFKA_BROKERS="localhost:9092"

EXPOSE 9092

