package com.pro.recipe.DTO;

import jakarta.validation.constraints.*;

import java.util.List;

public record RecipeRequest(
        @Positive(message = "Id musi być liczbą dodatnią")
        Long id,

        @NotBlank(message = "creatorId jest wymagane")
        @Size(min = 36, max = 36, message = "creatorId musi mieć dokładnie 36 znaków (UUID)")
        String creatorId,

        byte[] imageData,

        @NotBlank(message = "prepTime jest wymagane")
        @Size(max = 30, message = "prepTime może mieć maks. 30 znaków")
        String prepTime,

        @NotBlank(message = "difficulty jest wymagane")
        @Size(max = 50, message = "difficulty może mieć maks. 50 znaków")
        String difficulty,

        @NotBlank(message = "description jest wymagana")
        String description,

        @NotEmpty(message = "ingredientIds nie może być puste")
        List<@Positive(message = "ingredientId musi być dodatni") Long> ingredientIds
) {}

