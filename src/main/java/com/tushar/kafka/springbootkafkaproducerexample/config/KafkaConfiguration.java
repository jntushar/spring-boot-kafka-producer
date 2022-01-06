package com.tushar.kafka.springbootkafkaproducerexample.config;

import com.tushar.kafka.springbootkafkaproducerexample.model.User;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    @Bean
    public ProducerFactory<String, User> producerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        //config.put(ProducerConfig.CLIENT_ID_CONFIG, "client_id_cognitree");

        config.put(ProducerConfig.RETRIES_CONFIG, 0);
        //Batch up to 64K buffer sizes
        config.put(ProducerConfig.BATCH_SIZE_CONFIG, 16_384);
        //Linger up to 100 ms before sending batch if size not met
        config.put(ProducerConfig.LINGER_MS_CONFIG, 10);
        config.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    public KafkaTemplate<String, User> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }


}