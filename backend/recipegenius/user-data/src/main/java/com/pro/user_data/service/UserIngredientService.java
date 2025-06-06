package com.pro.user_data.service;

import com.pro.user_data.DTO.IngredientRequest;
import com.pro.user_data.DTO.IngredientResponse;
import com.pro.user_data.entity.UserIngredient;
import com.pro.user_data.recipe.RecipeClient;
import com.pro.user_data.repository.UserIngredientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserIngredientService {

    private final UserIngredientRepository userIngredientRepository;
    private final RecipeClient recipeClient;

    @Transactional
    public void addIngredients(Authentication auth, IngredientRequest req) {
        req.ids().forEach(id ->
                userIngredientRepository.save(new UserIngredient(auth.getName(), id)));
    }

    @Transactional
    public void deleteIngredients(Authentication auth, IngredientRequest req) {
        req.ids().forEach(id ->
                userIngredientRepository.deleteByIdUserIdAndIdIngredientId(auth.getName(), id));
    }

    @Transactional
    public List<IngredientResponse> getAllIngredients() {
        return recipeClient.getAllIngredient();
    }

    @Transactional
    public List<IngredientResponse> getMyIngredients(Authentication auth) {
        List<Long> myIngredientIds = userIngredientRepository.findByIdUserId(auth.getName())
                .stream()
                .map(ui -> ui.getId().getIngredientId())
                .collect(Collectors.toList());

        IngredientRequest req = new IngredientRequest(myIngredientIds);
        return recipeClient.getAllIngredientByIds(req);
    }
}
