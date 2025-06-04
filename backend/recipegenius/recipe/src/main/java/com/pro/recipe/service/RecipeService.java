package com.pro.recipe.service;

import com.pro.recipe.DTO.RecipeRequest;
import com.pro.recipe.DTO.RecipeResponse;
import com.pro.recipe.entity.Ingredient;
import com.pro.recipe.entity.Recipe;
import com.pro.recipe.entity.RecipeIngredientId;
import com.pro.recipe.repository.IngredientRepository;
import com.pro.recipe.repository.RecipeIngredientRepository;
import com.pro.recipe.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepo;
    private final IngredientRepository ingredientRepo;
    private final RecipeIngredientRepository recipeIngRepo;
    private final RecipeMapper               mapper;

    public RecipeResponse createRecipe(RecipeRequest req, String creatorId) {
        Recipe recipe = mapper.toRecipe(req);
        recipe.setCreatorId(creatorId);
        //recipe = recipeRepo.save(recipe);
        addIngredientsInternal(recipe, req.ingredientIds());
        return mapper.fromRecipe(recipeRepo.findById(recipe.getId()).orElseThrow());
    }

    public RecipeResponse getRecipe(Long id) {
        return mapper.fromRecipe(
                recipeRepo.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Przepis %d nie istnieje".formatted(id)))
        );
    }

    public List<RecipeResponse> getAllRecipes() {
        return recipeRepo.findAll()
                .stream()
                .map(mapper::fromRecipe)
                .toList();
    }

    public void deleteRecipe(Long id) {
        recipeRepo.deleteById(id);
    }

    public RecipeResponse addIngredientToRecipe(Long recipeId, Long ingredientId) {
        Recipe recipe = recipeRepo.findById(recipeId)
                .orElseThrow(() -> new IllegalArgumentException("Przepis %d nie istnieje".formatted(recipeId)));
        Ingredient ing = ingredientRepo.findById(ingredientId)
                .orElseThrow(() -> new IllegalArgumentException("Składnik %d nie istnieje".formatted(ingredientId)));

        if (recipeIngRepo.existsByRecipeIdAndIngredientId(recipe.getId(), ing.getId())) {
            return mapper.fromRecipe(recipe);
        }
        recipeIngRepo.save(mapper.link(recipe, ing));
        return mapper.fromRecipe(recipe);
    }

    public RecipeResponse removeIngredientFromRecipe(Long recipeId, Long ingredientId) {
        recipeIngRepo.deleteById(new RecipeIngredientId(recipeId, ingredientId));
        return getRecipe(recipeId);
    }

    private void addIngredientsInternal(Recipe recipe, List<Long> ingredientIds) {
        if (ingredientIds == null || ingredientIds.isEmpty()) {
            return;
        }
        ingredientIds.stream()
                .map(id -> ingredientRepo.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Składnik %d nie istnieje".formatted(id))))
                .filter(ing -> !recipeIngRepo.existsByRecipeIdAndIngredientId(recipe.getId(), ing.getId()))
                .map(ing -> mapper.link(recipe, ing))
                .forEach(recipeIngRepo::save);
    }
}

