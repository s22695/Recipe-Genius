package com.pro.recipe.service;

import com.pro.recipe.DTO.IngredientIdsRequest;
import com.pro.recipe.DTO.IngredientResponse;
import com.pro.recipe.entity.Ingredient;
import com.pro.recipe.repository.IngredientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientMapper mapper;

    public List<IngredientResponse> getAllIngredients() {
        return ingredientRepository.findAll().stream().map(mapper::fromIngredient).toList();
    }

    public List<IngredientResponse> getAllIngredientsByIds(IngredientIdsRequest req) {
        List<Ingredient> ingredients = ingredientRepository.findAllById(req.ids());
        return ingredients.stream().map(mapper::fromIngredient).toList();
    }
}
