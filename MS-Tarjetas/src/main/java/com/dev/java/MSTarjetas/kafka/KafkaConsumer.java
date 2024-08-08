package com.dev.java.MSTarjetas.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "healthCheckTopic", groupId = "tarjetas-group")
    public void consumeStringMessage(String message) {
        System.out.println("Consumed String message: " + message);
    }
}
