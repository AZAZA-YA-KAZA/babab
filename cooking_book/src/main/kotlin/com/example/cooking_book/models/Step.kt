package com.example.cooking_book.models
import jakarta.persistence.*

/**
 * Сущность шага приготовления.
 * Позволяет сформировать пошаговую инструкцию для выполнения рецепта.
 *
 * @property idStep Уникальный идентификатор шага.
 * @property idRecipe Ссылка на ID рецепта, к которому относится данный шаг.
 * @property stepDescription Текстовое описание действий на данном этапе.
 * @property stepOrder Порядковый номер шага в последовательности приготовления.
 */
@Entity
@Table(name = "step")
data class Step(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_step")
    val idStep: Long? = null,

    @Column(name = "id_recipe")
    val idRecipe: Long,

    @Column(name = "step_description")
    val stepDescription: String,

    @Column(name = "step_order")
    val stepOrder: Int
) {
    /**
     * Вторичный конструктор для создания нового шага.
     * @param idRecipe ID связанного рецепта.
     * @param stepDescription Текст инструкции.
     * @param stepOrder Позиция в списке.
     */
    constructor(idRecipe: Long, stepDescription: String, stepOrder: Int) :
            this(null, idRecipe, stepDescription, stepOrder)
}