package com.example.cooking_book.models

/**
 * Объект запроса для создания или редактирования основного профиля рецепта.
 * Содержит базовую информацию, необходимую для отображения рецепта в общем списке.
 *
 * @property title Название блюда.
 * @property description Подробное описание процесса, история блюда или заметки автора.
 * @property photoUrl Ссылка на изображение готового блюда для визуализации в интерфейсе.
 */
data class RecipeRequest(
    val title: String,
    val description: String,
    val photoUrl: String
)