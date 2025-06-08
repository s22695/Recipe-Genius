package com.pro.user_data.service;

import com.pro.user_data.DTO.*;
import com.pro.user_data.entity.UserRecipe;
import com.pro.user_data.recipe.RecipeClient;
import com.pro.user_data.repository.UserIngredientRepository;
import com.pro.user_data.repository.UserRecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRecipeService {

    private final UserIngredientRepository userIngredientRepository;
    private final UserRecipeRepository userRecipeRepository;
    private final RecipeClient recipeClient;

    @Transactional
    public void addRecipe(Authentication auth, UserRecipeRequest req) {
        req.ids().forEach(id ->
                userRecipeRepository.save(new UserRecipe(auth.getName(), id))
        );
    }

    @Transactional
    public void deleteRecipe(Authentication auth, UserRecipeRequest req) {
        req.ids().forEach(id ->
                userRecipeRepository.deleteByIdUserIdAndIdRecipeId(auth.getName(), id));
    }

    @Transactional
    public List<RecipeResponseWithPercentage> getRecipesWithMatchPercent(Authentication auth) {

        Set<Long> myIngredientIds = userIngredientRepository.findByIdUserId(auth.getName())
                .stream()
                .map(ui -> ui.getId().getIngredientId())
                .collect(Collectors.toSet());

        List<RecipeResponse> recipes = recipeClient.getAllRecipe();

        return recipes.stream()
                .map(rec -> mapWithPercent(rec, myIngredientIds))
                .sorted(Comparator.comparing(RecipeResponseWithPercentage::percentage).reversed())
                .toList();
    }

    @Transactional
    public List<RecipeResponseWithPercentage> getMyRecipesWithMatchPercent(Authentication auth) {

        Set<Long> myIngredientsIds = userIngredientRepository.findByIdUserId(auth.getName())
                .stream()
                .map(ui -> ui.getId().getIngredientId())
                .collect(Collectors.toSet());

        List<Long> myRecipesIds = userRecipeRepository.findByIdUserId(auth.getName())
                .stream()
                .map(ui -> ui.getId().getRecipeId())
                .collect(Collectors.toList());

        if (myRecipesIds.size() <= 0) {
            return new ArrayList<>();
        }

        List<RecipeResponse> recipes = recipeClient.getAllRecipeByIds(new UserRecipeRequest(myRecipesIds));

        return recipes.stream()
                .map(rec -> mapWithPercent(rec, myIngredientsIds))
                .toList();
    }

    private RecipeResponseWithPercentage mapWithPercent(RecipeResponse r, Set<Long> myIds) {

        long total = r.ingredients().size();
        long matched = r.ingredients().stream()
                .filter(ir -> myIds.contains(ir.id()))
                .count();

        double percent = total == 0 ? 0d : (matched * 100.0) / total;

        return new RecipeResponseWithPercentage(
                r.id(),
                Math.round(percent * 10.0) / 10.0,
                r.creatorId(),
                r.image(),
                r.prepTime(),
                r.difficulty(),
                r.description(),
                r.steps(),
                r.ingredients()
        );
    }
}
