package com.example.demo.controllers.auth;

import com.example.demo.controllers.BaseController;
import com.example.demo.dto.requests.auth.LoginRequest;
import com.example.demo.services.auth.AuthService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("${api.prefix}/auth")
@Setter
@Getter
public class AuthController extends BaseController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        setAuthService(authService);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        Map<String, String> tokens = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        if (tokens != null) {
            return ResponseEntity.ok(tokens);
        } else {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
    }
}