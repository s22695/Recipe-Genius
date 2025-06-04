package com.pro.recipe.service;

import com.pro.recipe.DTO.RecipeRequest;
import com.pro.recipe.DTO.RecipeResponse;
import com.pro.recipe.entity.Ingredient;
import com.pro.recipe.entity.Recipe;
import com.pro.recipe.entity.RecipeIngredient;
import com.pro.recipe.entity.RecipeIngredientId;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RecipeMapper {

    public Recipe toRecipe(RecipeRequest req) {
        if (req == null) {
            return null;
        }
        return Recipe.builder()
                .id(req.id())
                .creatorId(req.creatorId())
                .imageData(req.imageData())
                .prepTime(req.prepTime())
                .difficulty(req.difficulty())
                .description(req.description())
                .build();
    }

    public RecipeResponse fromRecipe(@Nullable Recipe recipe) {
        if (recipe == null) {
            return null;
        }
        List<Long> ingredientIds = recipe.getRecipeIngredients()
                .stream()
                .map(ri -> ri.getIngredient().getId())
                .toList();

        return new RecipeResponse(
                recipe.getId(),
                recipe.getCreatorId(),
                recipe.getPrepTime(),
                recipe.getDifficulty(),
                recipe.getDescription(),
                ingredientIds
        );
    }

    public RecipeIngredient link(Recipe recipe, Ingredient ingredient) {
        return RecipeIngredient.builder()
                .id(new RecipeIngredientId(recipe.getId(), ingredient.getId()))
                .recipe(recipe)
                .ingredient(ingredient)
                .build();
    }
}

