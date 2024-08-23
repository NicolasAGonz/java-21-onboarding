package com.dev.java.MSCuentas.kafka;

import com.dev.java.MSCuentas.dto.NewUserWithProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "healthCheckTopic", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void consumeStringMessage(String message) {
        System.out.println("Consumed String message: " + message);
    }

    @KafkaListener(topics = "newUserCreatedTopic", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerProductFactory")
    public void consumeCrearCuenta(String record) {

        logger.info("RECIBI EL SIGUIENTE MENSAJE: ");
        logger.info(record);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            NewUserWithProductDTO dto = objectMapper.readValue(record, NewUserWithProductDTO.class);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
