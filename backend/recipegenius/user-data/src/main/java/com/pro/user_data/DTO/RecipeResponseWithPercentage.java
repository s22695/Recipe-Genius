package com.pro.user_data.DTO;

import java.util.List;

public record RecipeResponseWithPercentage(
        Long id,
        Double percentage,
        String creatorId,
        String prepTime,
        String difficulty,
        String description,
        String steps,
        List<IngredientResponse> ingredients
) { }
