package com.pro.client_service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/findAll")
    public ResponseEntity<String> findAll(Authentication authentication) {
        return ResponseEntity.ok(authentication.getCredentials().toString());
    }

}
