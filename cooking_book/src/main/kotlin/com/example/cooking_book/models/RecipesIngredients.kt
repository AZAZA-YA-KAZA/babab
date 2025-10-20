package com.example.cooking_book.models

import jakarta.persistence.*

@Entity
@Table(name = "recipe_ingredient")
data class RecipesIngredients(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recipe_ingredient")
    val idRecipeIngredient: Long? = null,

    @Column(name = "id_recipe")
    val idRecipe: Long,

    @Column(name = "id_ingredient")
    val idIngredient: Long,

    @Column(name = "count")
    val count: Int,

    @Column(name = "unit")
    val unit: String
) {
    constructor(idRecipe: Long, idIngredient: Long, count: Int, unit: String) :
            this(null, idRecipe, idIngredient, count, unit)
}
