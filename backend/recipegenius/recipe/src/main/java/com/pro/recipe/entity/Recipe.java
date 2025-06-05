package com.pro.recipe.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipe")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creator_id", nullable = false, length = 36)
    private String creatorId;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Lob
    @Column(name = "image_data", columnDefinition = "BYTEA")
    private byte[] imageData;

    @Column(name = "prep_time", length = 30)
    private String prepTime;

    @Column(name = "difficulty", length = 30)
    private String difficulty;

    @Column(name = "description")
    private String description;

    @Column(name = "steps")
    private String steps;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<RecipeIngredient> recipeIngredients = new HashSet<>();
}
