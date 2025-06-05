package com.pro.recipe.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecipeIngredientId implements Serializable {
    private Long recipeId;
    private Long ingredientId;
}
