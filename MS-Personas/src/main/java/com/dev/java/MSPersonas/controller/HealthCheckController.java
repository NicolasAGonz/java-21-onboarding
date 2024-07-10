package com.dev.java.MSPersonas.controller;

import com.dev.java.MSPersonas.service.HealthCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/probe")
@RequiredArgsConstructor
public class HealthCheckController {

    private final HealthCheckService healthCheckService;

    @GetMapping("/healthcheck")
    public ResponseEntity<String> checkHealth() {
        return healthCheckService.status();
    }
}
