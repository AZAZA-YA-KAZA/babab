package com.example.cooking_book.models
import jakarta.persistence.*

/**
 * Сущность, представляющая базовую информацию об ингредиенте.
 * Используется как справочник названий продуктов для исключения дублирования.
 *
 * @property idIngredient Уникальный идентификатор ингредиента (первичный ключ).
 * @property name Название продукта (например, «Мука пшеничная», «Молоко»).
 */
@Entity
@Table(name = "ingredient")
data class Ingredient(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingredient")
    val idIngredient: Long? = null,

    @Column(name = "nam", length = 50)
    val name: String
) {
    /**
     * Вторичный конструктор для создания ингредиента без указания ID.
     * @param name Название ингредиента.
     */
    constructor(name: String) : this(null, name)
}