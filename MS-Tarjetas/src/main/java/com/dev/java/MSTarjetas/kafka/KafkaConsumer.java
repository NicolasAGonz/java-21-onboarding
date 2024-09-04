package com.dev.java.MSTarjetas.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "healthCheckTopic", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerHealthCheckFactory")
    public void consumeStringMessage(String message) {
        logger.info("Consumed String message: " + message);
    }


    @KafkaListener(topics = "newAccountsCreatedTopic", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerAccountFactory")
    public void consumeCardCreation(String newAccountNumCue){
        logger.info("SE RECIBIO UN NUEVO NUMERO DE CUENTA: " + newAccountNumCue);
        logger.info("SE PROCEDERA A DAR DE ALTA LAS TARJETAS ASOCIADAS");

    }



}
