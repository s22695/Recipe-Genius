package com.pro.recipe.DTO;

import java.util.List;

public record RecipeResponse(
        Long id,
        String creatorId,
        String prepTime,
        String difficulty,
        String description,
        List<Long> ingredientIds
) { }
