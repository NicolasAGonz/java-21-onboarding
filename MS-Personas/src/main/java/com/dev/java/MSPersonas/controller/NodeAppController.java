package com.dev.java.MSPersonas.controller;

import com.dev.java.MSPersonas.service.NodeServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NodeAppController {
    private final NodeServiceClient nodeServiceClient;

    @Autowired
    public NodeAppController(NodeServiceClient nodeServiceClient) {
        this.nodeServiceClient = nodeServiceClient;
    }

    @GetMapping("/fetch-worldsys")
    public String fetchWorldsysData(@RequestParam String dni) {
        return nodeServiceClient.getWorldsysData(dni);
    }

    @GetMapping("/fetch-veraz")
    public String fetchVerazData(@RequestParam String dni) {
        return nodeServiceClient.getVerazData(dni);
    }

    @GetMapping("/fetch-renaper")
    public String fetchRenaperData(@RequestParam String dni) {
        return nodeServiceClient.getRenaperData(dni);
    }
}
