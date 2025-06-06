package com.pro.user_data.controller;

import com.pro.user_data.DTO.IngredientRequest;
import com.pro.user_data.DTO.IngredientResponse;
import com.pro.user_data.recipe.RecipeClient;
import com.pro.user_data.service.UserIngredientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-recipe")
@RequiredArgsConstructor
public class UserRecipeController {

    private final UserIngredientService service;
    private final RecipeClient recipeClient;

    @GetMapping("/add")
    public ResponseEntity<List<IngredientResponse>> addRecipe(
            Authentication authentication) {
        return ResponseEntity.ok(service.getMyIngredients(authentication));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteRecipe(Authentication auth,
                                             @RequestBody @Valid IngredientRequest req) {
        //service.deleteIngredients(auth, req);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/allFavorites")
    public ResponseEntity<Void> allFavorites(
            Authentication authentication) {
        //service.addIngredients(authentication, body);
        return ResponseEntity.ok().build();
    }
}

