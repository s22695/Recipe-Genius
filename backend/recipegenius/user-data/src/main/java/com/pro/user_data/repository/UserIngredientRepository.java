package com.pro.user_data.repository;

import com.pro.user_data.entity.UserIngredient;
import com.pro.user_data.entity.UserIngredientId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserIngredientRepository
        extends JpaRepository<UserIngredient, UserIngredientId> {

    List<UserIngredient> findByIdUserId(String userId);

    void deleteByIdUserIdAndIdIngredientId(String userId, Long ingredientId);
}
