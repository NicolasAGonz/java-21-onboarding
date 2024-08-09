package com.dev.java.MSPersonas.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class NodeServiceClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public NodeServiceClient(RestTemplate restTemplate, @Value("${node.service.baseurl}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public String getWorldsysData(String dni) {
        String url = String.format("%s/service/worldsys?dni=%s", baseUrl, dni);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

    public String getVerazData(String dni) {
        String url = String.format("%s/service/veraz?dni=%s", baseUrl, dni);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

    public String getRenaperData(String dni) {
        String url = String.format("%s/service/renaper?dni=%s", baseUrl, dni);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}

