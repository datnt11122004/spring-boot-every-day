package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("${api.prefix}")
public class HealthCheckController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        // Custom health check logic
        boolean isHealthy = checkHealth();
        Map<String, String> response = new HashMap<>();
        if (isHealthy) {
            response.put("status", "OK");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "INTERNAL_SERVER_ERROR");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean checkHealth() {
        // Implement your custom health check logic here
        return true;
    }
}