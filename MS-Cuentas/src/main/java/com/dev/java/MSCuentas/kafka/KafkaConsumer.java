package com.dev.java.MSCuentas.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "healthCheckTopic", groupId = "cuentas-group")
    public void consumeStringMessage(String message) {
        System.out.println("Consumed String message: " + message);
    }
}
