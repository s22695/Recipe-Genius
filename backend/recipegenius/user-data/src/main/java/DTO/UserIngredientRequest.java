package DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record UserIngredientRequest(
        @NotEmpty(message = "ingredientIds nie mogą być puste")
        List<@Positive(message = "ingredientId musi być dodatni") Long> ingredientIds
) {}

