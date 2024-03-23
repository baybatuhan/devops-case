import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerExample {
    public static void main(String[] args) {
        // Kafka sunucusu adresi ve portu
        String bootstrapServers = "localhost:9092";

        // Kafka konfigürasyon ayarları
        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("group.id", "test-group"); // Tüketici grubu kimliği
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // Kafka tüketici oluşturma
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // Konusu "my-topic" olan konuyu dinleme
        consumer.subscribe(Collections.singletonList("my-topic"));

        // Mesajları dinleme döngüsü
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100); // 100 ms içinde yeni mesajları al
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Konu: " + record.topic() +
                        ", Partisyon: " + record.partition() +
                        ", Offset: " + record.offset() +
                        ", Key: " + record.key() +
                        ", Mesaj: " + record.value());
            }
        }
    }
}
