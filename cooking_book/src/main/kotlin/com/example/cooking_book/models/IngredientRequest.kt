package com.example.cooking_book.models

/**
 * Объект запроса для добавления или изменения ингредиента в рецепте.
 * Используется для передачи данных о составе блюда от фронтенда к сервису.
 *
 * @property name Название ингредиента (например, "Сахар").
 * @property count Количество данного ингредиента.
 * @property unit Единица измерения (например, "гр", "ст. ложка").
 */
data class IngredientRequest(
    val name: String,
    val count: Int,
    val unit: String
)