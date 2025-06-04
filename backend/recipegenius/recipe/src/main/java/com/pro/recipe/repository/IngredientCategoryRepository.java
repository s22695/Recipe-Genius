package com.pro.recipe.repository;

import com.pro.recipe.entity.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientCategoryRepository
        extends JpaRepository<IngredientCategory, Long> {

    Optional<IngredientCategory> findByIngredientName(String ingredientName);
}
