package com.pro.recipe.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recipe_ingredient")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecipeIngredient {

    @EmbeddedId
    private RecipeIngredientId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recipeId")
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
}
