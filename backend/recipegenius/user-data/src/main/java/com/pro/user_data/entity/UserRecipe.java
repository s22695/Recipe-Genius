package com.pro.user_data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_recipe")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRecipe {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private UserRecipeId id;

    public UserRecipe(String userId, Long recipeId) {
        this.id = new UserRecipeId(userId, recipeId);
    }
}
