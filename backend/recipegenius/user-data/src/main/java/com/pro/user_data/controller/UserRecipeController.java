package com.pro.user_data.controller;

import DTO.UserIngredientRequest;
import DTO.UserRecipeRequest;
import com.pro.user_data.DTO.IngredientResponse;
import com.pro.user_data.DTO.RecipeResponse;
import com.pro.user_data.DTO.RecipeResponseWithPercentage;
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
    public ResponseEntity<List<RecipeResponseWithPercentage>> addRecipe(
            Authentication authentication) {
        return ResponseEntity.ok(service.getRecipesWithMatchPercent(authentication));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteRecipe(Authentication auth,
                                             @RequestBody @Valid UserIngredientRequest req) {
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

