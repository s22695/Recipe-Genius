package com.pro.user_data.recipe;

import com.pro.user_data.DTO.IngredientResponse;
import com.pro.user_data.DTO.RecipeResponse;
import com.pro.user_data.DTO.IngredientRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "recipe-service",
        url = "${application.config.recipe-url}"
)
public interface RecipeClient {
    @GetMapping("/getAllIngredient")
    List<IngredientResponse> getAllIngredient();

    @GetMapping("/getAllRecipe")
    List<RecipeResponse> getAllRecipe();

    @PostMapping("/getAllIngredientByIds")
    List<IngredientResponse> getAllIngredientByIds(@RequestBody IngredientRequest req);
}
