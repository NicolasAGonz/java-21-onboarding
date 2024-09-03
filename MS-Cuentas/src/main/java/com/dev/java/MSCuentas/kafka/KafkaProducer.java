package com.dev.java.MSCuentas.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String TOPIC_NEW_ACCOUNTS = "newAccountsCreatedTopic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateNewAccountsDTO;

    public void sendNewAccountCreatedMessage(String newAccountNumCue) {
        kafkaTemplateNewAccountsDTO.send(TOPIC_NEW_ACCOUNTS, newAccountNumCue );
    }

}
