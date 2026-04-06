package com.example.cooking_book.repository

import com.example.cooking_book.models.Ingredient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

/**
 * Репозиторий для работы с сущностями ингредиентов.
 */
interface IngredientRepository : JpaRepository<Ingredient, Int> {
    /**
     * Поиск идентификатора ингредиента по его точному названию.
     * Используется для проверки существования ингредиента перед созданием нового.
     *
     * @param name Название ингредиента.
     * @return ID ингредиента, если он найден, иначе null.
     */
    @Query("SELECT idIngredient FROM Ingredient WHERE name = :name")
    fun getIdByName(@Param("name") name: String): Long?
}