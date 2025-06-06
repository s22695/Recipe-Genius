package com.pro.recipe.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record IngredientIdsRequest(
        @NotEmpty(message = "Lista id nie może być pusta")
        List<@Positive(message = "Id musi być większe od zera") Long> ids
) {}
