package com.example.cooking_book.models

import jakarta.persistence.*
import lombok.Data
import lombok.NoArgsConstructor

/**
 * Основная сущность рецепта в кулинарной книге.
 * Содержит заголовок, краткое описание и ссылку на визуальное представление блюда.
 *
 * @property idRecipe Уникальный идентификатор рецепта (первичный ключ).
 * @property title Название блюда (отображается в списке и при поиске).
 * @property description Краткое описание рецепта или заметки автора.
 * @property photoUrl URL-адрес или путь к загруженной фотографии готового блюда.
 */
@Table(name = "recipe")
@Entity
@Data
@NoArgsConstructor
data class Recipe(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recipe")
    val idRecipe: Long? = null,

    @Column(name = "title", length = 50)
    var title: String,

    @Column(name = "description")
    var description: String,

    @Column(name = "photo_url")
    var photoUrl: String
) {
    /**
     * Конструктор по умолчанию для JPA, инициализирующий поля пустыми строками.
     */
    constructor() : this(title = "", description = "", photoUrl = "")
}