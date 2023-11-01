package com.tushar.kafka.springbootkafkaproducerexample.Listener;

import com.tushar.kafka.springbootkafkaproducerexample.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import static com.tushar.kafka.springbootkafkaproducerexample.common.Constants.WIKIMEDIA_RECENT_CHANGE_URL;
import static com.tushar.kafka.springbootkafkaproducerexample.common.Constants.WIKIMEDIA_TOPIC;

@Slf4j
@Component
public class WikimediaListener implements CommandLineRunner {

    @Autowired
    private ProducerService<String, String> producerService;

    private final WebClient webClient;

    public WikimediaListener(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(WIKIMEDIA_RECENT_CHANGE_URL).build();
    }


    @Override
    public void run(String... args) throws Exception {
        webClient.get()
                .retrieve()
                .bodyToFlux(String.class)
                .subscribe(this::handleData);
    }

    private void handleData(String data) {
        producerService.sendToTopic(WIKIMEDIA_TOPIC, data);
    }
}
