package com.pro.user_data.controller;

import com.pro.user_data.DTO.RecipeResponseWithPercentage;
import com.pro.user_data.DTO.UserRecipeRequest;
import com.pro.user_data.service.UserRecipeService;
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

    private final UserRecipeService service;

    @PostMapping("/add")
    public ResponseEntity<Void> addRecipe(
            Authentication authentication,
            @RequestBody @Valid UserRecipeRequest req) {
        service.addRecipe(authentication, req);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteRecipe(Authentication auth,
                                             @RequestBody @Valid UserRecipeRequest req) {
        service.deleteRecipe(auth, req);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/allFavorites")
    public ResponseEntity<List<RecipeResponseWithPercentage>> allFavorites(
            Authentication authentication) {
        return ResponseEntity.ok(service.getMyRecipesWithMatchPercent(authentication));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RecipeResponseWithPercentage>> all(
            Authentication authentication) {
        return ResponseEntity.ok(service.getRecipesWithMatchPercent(authentication));
    }
}

