package com.dev.java.MSPersonas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthCheckService {

    private final KafkaTemplate kafkaTemplate;
    private static final String healthCheckTopic = "healthCheckTopic";

    public ResponseEntity<String> status (){

        kafkaTemplate.send(healthCheckTopic, "ESTADO DE LA APLICACION CONSULTADO");

        return new ResponseEntity<>("Application up and running", HttpStatus.OK);
    }
}
