package com.pro.user_data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRecipeId implements Serializable {

    @Column(name = "user_id", length = 36)
    private String userId;

    @Column(name = "recipe_id")
    private Long recipeId;
}
