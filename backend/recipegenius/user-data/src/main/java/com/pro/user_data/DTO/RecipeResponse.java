package com.pro.user_data.DTO;

import java.util.List;

public record RecipeResponse(
        Long id,
        String creatorId,
        byte[] image,
        String prepTime,
        String difficulty,
        String description,
        String steps,
        List<IngredientResponse> ingredients
) { }
