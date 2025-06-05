package com.pro.recipe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "ingredient_category")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IngredientCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ingredient_name", nullable = false, unique = true, length = 255)
    private String ingredientName;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Ingredient> ingredients;
}
