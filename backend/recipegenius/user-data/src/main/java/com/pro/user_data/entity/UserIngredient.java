package com.pro.user_data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_ingredient")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserIngredient {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private UserIngredientId id;

    public UserIngredient(String userId, Long ingredientId) {
        this.id = new UserIngredientId(userId, ingredientId);
    }
}

