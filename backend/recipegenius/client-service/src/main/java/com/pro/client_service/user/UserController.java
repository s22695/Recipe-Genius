package com.pro.client_service.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<Long> createUser(
            @RequestBody @Valid UserRequest request
    ) {
        return ResponseEntity.ok(this.service.createUser(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid UserRequest request
    ) {
        this.service.updateUser(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(this.service.findAllUsers());
    }

    @GetMapping("/exists/{user-id}")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("user-id") Long userId
    ) {
        return ResponseEntity.ok(this.service.existsById(userId));
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserResponse> findById(
            @PathVariable("user-id") Long userId
    ) {
        return ResponseEntity.ok(this.service.findById(userId));
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("user-id") Long userId
    ) {
        this.service.deleteUser(userId);
        return ResponseEntity.accepted().build();
    }
}
