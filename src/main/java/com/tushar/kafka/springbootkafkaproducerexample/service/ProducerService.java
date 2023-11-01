package com.tushar.kafka.springbootkafkaproducerexample.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class ProducerService<K, V> {

    @Autowired
    private KafkaTemplate<K, V> kafkaTemplate;

    public void sendToTopic(String topic, @Nullable V data) {
        ListenableFuture<SendResult<K, V>> future = kafkaTemplate.send(topic, data);
        handleFuture(future);
    }

    public void sendToTopic(String topic, K key, @Nullable V data) {
        ListenableFuture<SendResult<K, V>> future = kafkaTemplate.send(topic, key, data);
        handleFuture(future);
    }

    private void handleFuture(ListenableFuture<SendResult<K, V>> future) {
        future.addCallback(new ListenableFutureCallback<SendResult<K, V>>() {
            @Override
            public void onSuccess(SendResult<K, V> result) {
                log.info("Message sent successfully: Topic={} | Partition={} | Offset={}",
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Failed to send message {}", ex.getMessage());
            }
        });
    }
}
