package com.pro.recipe;

import com.pro.recipe.DTO.*;
import com.pro.recipe.service.IngredientService;
import com.pro.recipe.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    @GetMapping("/test")
    public String findAll(Authentication authentication) {
        return "Test from recipe + " + authentication.getName();
    }

    @PostMapping("/create")
    public ResponseEntity<RecipeResponse> createRecipe(
            Authentication authentication,
            @RequestBody @Valid RecipeRequest recipeRequest) {
        return ResponseEntity.ok(recipeService.createRecipe(recipeRequest, authentication.getName()));
    }

    @GetMapping("/getAllRecipe")
    public ResponseEntity<List<RecipeResponse>> getAllRecipe() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping("/getAllIngredient")
    public ResponseEntity<List<IngredientResponse>> getAllIngredient() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @PostMapping("/getAllIngredientByIds")
    public ResponseEntity<List<IngredientResponse>> getAllIngredientByIds(
            @RequestBody @Valid IngredientIdsRequest req
    ) {
        return ResponseEntity.ok(ingredientService.getAllIngredientsByIds(req));
    }

    @PostMapping("/getAllRecipeByIds")
    public ResponseEntity<List<RecipeResponse>> getAllRecipeByIds(
            @RequestBody @Valid RecipeIdsRequest req
    ) {
        return ResponseEntity.ok(recipeService.getRecipesByIds(req));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<RecipeResponse>> getRecipesByIds(
            @RequestBody @Valid RecipeIdsRequest req) {
        List<RecipeResponse> result = recipeService.getRecipesByIds(req);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }

}
