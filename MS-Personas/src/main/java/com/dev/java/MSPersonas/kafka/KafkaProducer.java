package com.dev.java.MSPersonas.kafka;

import com.dev.java.MSPersonas.dto.NewUserWithProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final String TOPIC_NEW_USER_WITH_PRODUCT = "newUserCreatedTopic";
    private static final String TOPIC_STRING_MESSAGE = "healthCheckTopic";

    @Autowired
    private KafkaTemplate<String, NewUserWithProductDTO> kafkaTemplateNewUserDTO;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateHealthCheck;

    public void sendNewUserWithProductMessage(NewUserWithProductDTO newUserWithProductDTO) {
        kafkaTemplateNewUserDTO.send(TOPIC_NEW_USER_WITH_PRODUCT, newUserWithProductDTO);
    }

    public void sendHealthCheckMessage(String message) {
        kafkaTemplateHealthCheck.send(TOPIC_STRING_MESSAGE, message);
    }
}
