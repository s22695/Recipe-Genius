package com.pro.user_data.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record UserRecipeRequest(
        @NotEmpty(message = "ingredientIds nie mogą być puste")
        List<@Positive(message = "ingredientId musi być dodatni") Long> ingredientIds
) {}
