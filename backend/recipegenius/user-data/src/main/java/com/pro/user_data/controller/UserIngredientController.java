package com.pro.user_data.controller;

import com.pro.user_data.DTO.IngredientRequest;
import com.pro.user_data.DTO.IngredientResponse;
import com.pro.user_data.service.UserIngredientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-ingredient")
@RequiredArgsConstructor
public class UserIngredientController {

    private final UserIngredientService service;

    @PostMapping("/add")
    public ResponseEntity<Void> addIngredient(
            Authentication authentication,
            @RequestBody @Valid IngredientRequest req) {
        service.addIngredients(authentication, req);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteIngredient(Authentication auth,
                                                 @RequestBody @Valid IngredientRequest req) {
        service.deleteIngredients(auth, req);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/allFavorites")
    public ResponseEntity<List<IngredientResponse>> allFavorites(
            Authentication authentication) {
        return ResponseEntity.ok(service.getMyIngredients(authentication));
    }

    @GetMapping("/all")
    public ResponseEntity<List<IngredientResponse>> all(
            Authentication authentication) {
        return ResponseEntity.ok(service.getAllIngredients());
    }
}
