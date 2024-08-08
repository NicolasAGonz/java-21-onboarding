package com.dev.java.MSPersonas.service;

import com.dev.java.MSPersonas.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthCheckService {

    private final KafkaProducer kafkaProducer;


    public ResponseEntity<String> status (){

        kafkaProducer.sendHealthCheckMessage( "ESTADO_DE_LA_APLICACION_CONSULTADO");

        return new ResponseEntity<>("Application up and running", HttpStatus.OK);
    }
}
