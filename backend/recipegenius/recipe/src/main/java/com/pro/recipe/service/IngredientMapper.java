package com.pro.recipe.service;

import com.pro.recipe.DTO.IngredientResponse;
import com.pro.recipe.entity.Ingredient;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IngredientMapper {
    public IngredientResponse fromIngredient(@Nullable Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }
        return new IngredientResponse(
                ingredient.getId(),
                ingredient.getIngredientName(),
                ingredient.getCategory().getIngredientName()
        );
    }
}
