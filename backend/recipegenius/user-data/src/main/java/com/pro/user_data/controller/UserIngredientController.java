package com.pro.user_data.controller;

import com.pro.user_data.DTO.UserIngredientRequest;
import com.pro.user_data.service.UserIngredientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-ingredient")
@RequiredArgsConstructor
public class UserIngredientController {

    private final UserIngredientService service;

    @PostMapping("/add")
    public ResponseEntity<Void> addIngredient(
            Authentication authentication,
            @RequestBody @Valid UserIngredientRequest req) {
        service.addIngredients(authentication, req);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteIngredient(Authentication auth,
                                                 @RequestBody @Valid UserIngredientRequest req) {
        service.deleteIngredients(auth, req);
        return ResponseEntity.noContent().build();
    }
}
