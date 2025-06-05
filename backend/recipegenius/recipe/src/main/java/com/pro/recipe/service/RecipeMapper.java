package com.pro.recipe.service;

import com.pro.recipe.DTO.IngredientResponse;
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
                .imageData(req.imageData())
                .prepTime(req.prepTime())
                .difficulty(req.difficulty())
                .description(req.description())
                .steps(req.steps())
                .build();
    }

    public RecipeResponse fromRecipe(@Nullable Recipe recipe) {
        if (recipe == null) {
            return null;
        }
        List<IngredientResponse> ingredients = (
                recipe.getRecipeIngredients() == null
                        ? List.of()
                        : recipe.getRecipeIngredients().stream()
                        .map(ri -> {
                            var ing = ri.getIngredient();
                            return new IngredientResponse(
                                    ing.getId(),
                                    ing.getIngredientName(),
                                    ing.getCategory().getIngredientName()
                            );
                        })
                        .toList()
        );

        return new RecipeResponse(
                recipe.getId(),
                recipe.getCreatorId(),
                recipe.getPrepTime(),
                recipe.getDifficulty(),
                recipe.getDescription(),
                recipe.getSteps(),
                ingredients
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

