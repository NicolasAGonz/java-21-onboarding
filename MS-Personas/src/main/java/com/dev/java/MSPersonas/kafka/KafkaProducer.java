package com.dev.java.MSPersonas.kafka;

import com.dev.java.MSPersonas.dto.NewUserWithProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String TOPIC_NEW_USER_WITH_PRODUCT = "newUserCreatedTopic";
    private static final String TOPIC_STRING_MESSAGE = "healthCheckTopic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateNewUserDTO;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateHealthCheck;

    public void sendNewUserWithProductMessage(NewUserWithProductDTO newUserWithProductDTO) {
        try {
            // Convierte el DTO a JSON String
            String jsonString = objectMapper.writeValueAsString(newUserWithProductDTO);
            // Envía el JSON String al topic
            kafkaTemplateNewUserDTO.send(TOPIC_NEW_USER_WITH_PRODUCT, jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    public void sendHealthCheckMessage(String message) {
        kafkaTemplateHealthCheck.send(TOPIC_STRING_MESSAGE, message);
    }
}
