package com.pro.recipe.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ingredient")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private IngredientCategory category;

    @Column(name = "ingredient_name", nullable = false, unique = true, length = 255)
    private String ingredientName;
}
