package com.pro.auth;

import com.pro.auth.DTOs.CreateUserRequest;
import com.pro.auth.DTOs.LoginRequest;
import com.pro.auth.DTOs.RefreshRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class KeycloakAuthController {
    private final KeycloakAuthService service;

    public KeycloakAuthController(KeycloakAuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        return service.login(req);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshRequest req) {
        return service.refresh(req);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest req) {
        return service.createUser(req);
    }
}
