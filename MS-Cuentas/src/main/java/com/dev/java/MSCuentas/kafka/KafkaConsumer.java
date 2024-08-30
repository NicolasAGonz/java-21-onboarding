package com.dev.java.MSCuentas.kafka;

import com.dev.java.MSCuentas.dto.NewUserWithProductDTO;
import com.dev.java.MSCuentas.service.CuentaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(KafkaConsumer.class);
    private final CuentaService cuentaService;

    @KafkaListener(topics = "healthCheckTopic", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void consumeStringMessage(String message) {
        System.out.println("Consumed String message: " + message);
    }

    @KafkaListener(topics = "newUserCreatedTopic", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerProductFactory")
    public void consumeCrearCuenta(String record) {

        logger.info("RECIBI EL SIGUIENTE MENSAJE: ");
        logger.info(record);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            NewUserWithProductDTO dto = objectMapper.readValue(record, NewUserWithProductDTO.class);

            logger.info("ARME EL SIGUIENTE DTO CON LA SIGUIENTE INFORMACION: ");
            logger.info(dto.toString());
            logger.info("LLAMANDO AL SERVICIO DE CREACION DE CUENTA...");
            cuentaService.crearCuenta(dto);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
