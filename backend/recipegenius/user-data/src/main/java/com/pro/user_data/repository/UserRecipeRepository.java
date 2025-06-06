package com.pro.user_data.repository;

import com.pro.user_data.entity.UserIngredientId;
import com.pro.user_data.entity.UserRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRecipeRepository
        extends JpaRepository<UserRecipe, UserIngredientId> {

    List<UserRecipe> findByIdUserId(String userId);

    void deleteByIdUserIdAndIdRecipeId(String userId, Long recipeId);
}

