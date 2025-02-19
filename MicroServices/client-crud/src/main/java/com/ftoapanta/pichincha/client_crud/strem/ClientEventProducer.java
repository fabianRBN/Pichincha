package com.ftoapanta.pichincha.client_crud.strem;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClientEventProducer {

    private static final String TOPIC = "client-events";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ClientEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendClientEvent(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}