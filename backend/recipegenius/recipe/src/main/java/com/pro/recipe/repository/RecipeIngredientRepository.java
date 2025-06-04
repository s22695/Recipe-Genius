package com.pro.recipe.repository;

import com.pro.recipe.entity.RecipeIngredient;
import com.pro.recipe.entity.RecipeIngredientId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeIngredientRepository
        extends JpaRepository<RecipeIngredient, RecipeIngredientId> {

    List<RecipeIngredient> findByRecipeId(Long recipeId);
    boolean existsByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
