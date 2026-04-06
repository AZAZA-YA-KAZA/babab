package com.example.cooking_book.models

/**
 * Объект запроса для формирования шага инструкции по приготовлению.
 * Позволяет структурировать последовательность действий пользователя.
 *
 * @property stepDescription Текстовое содержимое шага (инструкция к действию).
 * @property stepOrder Порядковый номер шага (используется для сортировки этапов приготовления).
 */
data class StepRequest(
    val stepDescription: String,
    val stepOrder: Int
)