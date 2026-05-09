package com.example.cooking_book.models
import jakarta.persistence.*

/**
 * Промежуточная сущность для связи рецепта с конкретными ингредиентами.
 * Определяет количественные характеристики продуктов для конкретного блюда.
 *
 * @property idRecipeIngredient Уникальный идентификатор записи связи.
 * @property idRecipe ID рецепта, в который входит ингредиент.
 * @property idIngredient ID используемого ингредиента.
 * @property count Количество продукта (числовое значение).
 * @property unit Единица измерения (например, «гр», «мл», «шт»).
 */
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
    /**
     * Вторичный конструктор для связки ингредиента и рецепта.
     * @param idRecipe ID рецепта.
     * @param idIngredient ID ингредиента.
     * @param count Кол-во.
     * @param unit Единица измерения.
     */
    constructor(idRecipe: Long, idIngredient: Long, count: Int, unit: String) :
            this(null, idRecipe, idIngredient, count, unit)
}