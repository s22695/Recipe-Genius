package com.pro.recipe;

import com.pro.recipe.DTO.RecipeRequest;
import com.pro.recipe.DTO.RecipeResponse;
import com.pro.recipe.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService service;

    @GetMapping("/test")
    public String findAll(Authentication authentication) {
        return "Test from recipe + " + authentication.getName();
    }

    @GetMapping("/create")
    public ResponseEntity<RecipeResponse> createRecipe(
            Authentication authentication,
            @RequestBody @Valid RecipeRequest recipeRequest) {
        return ResponseEntity.ok(service.createRecipe(recipeRequest, authentication.getName()));
    }

}
