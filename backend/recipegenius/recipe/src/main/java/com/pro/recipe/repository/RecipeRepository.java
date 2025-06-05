package com.pro.recipe.repository;

import com.pro.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("""
        SELECT DISTINCT r
        FROM Recipe r
        LEFT JOIN FETCH r.recipeIngredients ri
        LEFT JOIN FETCH ri.ingredient i
        LEFT JOIN FETCH i.category c
        WHERE r.id IN :ids
    """)
    List<Recipe> findAllWithIngredients(@Param("ids") List<Long> ids);
}
