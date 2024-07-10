package com.dev.java.MSPersonas.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckService {

    public ResponseEntity<String> status (){
        return new ResponseEntity<>("Application up and running", HttpStatus.OK);
    }
}
