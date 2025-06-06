package com.pro.user_data.service;

import com.pro.user_data.DTO.IngredientResponse;
import com.pro.user_data.DTO.RecipeResponse;
import com.pro.user_data.DTO.RecipeResponseWithPercentage;
import com.pro.user_data.DTO.IngredientRequest;
import com.pro.user_data.entity.UserIngredient;
import com.pro.user_data.recipe.RecipeClient;
import com.pro.user_data.repository.UserIngredientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserIngredientService {

    private final UserIngredientRepository repo;
    private final RecipeClient recipeClient;

    @Transactional
    public void addIngredients(Authentication auth, IngredientRequest req) {
        req.ids().forEach(id ->
                repo.save(new UserIngredient(auth.getName(), id)));
    }

    @Transactional
    public void deleteIngredients(Authentication auth, IngredientRequest req) {
        req.ids().forEach(id ->
                repo.deleteByIdUserIdAndIdIngredientId(auth.getName(), id));
    }

    @Transactional
    public List<RecipeResponseWithPercentage> getRecipesWithMatchPercent(Authentication auth) {

        Set<Long> myIngredientIds = repo.findByIdUserId(auth.getName())
                .stream()
                .map(ui -> ui.getId().getIngredientId())
                .collect(Collectors.toSet());

        List<RecipeResponse> recipes = recipeClient.getAllRecipe();

        return recipes.stream()
                .map(rec -> mapWithPercent(rec, myIngredientIds))
                .toList();
    }

    private RecipeResponseWithPercentage mapWithPercent(RecipeResponse r, Set<Long> myIds) {

        long total      = r.ingredients().size();
        long matched    = r.ingredients().stream()
                .filter(ir -> myIds.contains(ir.id()))
                .count();

        double percent  = total == 0 ? 0d : (matched * 100.0) / total;

        return new RecipeResponseWithPercentage(
                r.id(),
                Math.round(percent * 10.0) / 10.0,
                r.creatorId(),
                r.prepTime(),
                r.difficulty(),
                r.description(),
                r.steps(),
                r.ingredients()
        );
    }

    @Transactional
    public List<IngredientResponse> getMyIngredients(Authentication auth) {
        List<Long> myIngredientIds = repo.findByIdUserId(auth.getName())
                .stream()
                .map(ui -> ui.getId().getIngredientId())
                .collect(Collectors.toList());

        IngredientRequest req = new IngredientRequest(myIngredientIds);
        return recipeClient.getAllIngredientByIds(req);
    }
}
