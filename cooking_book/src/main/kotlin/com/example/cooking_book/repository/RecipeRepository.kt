package com.example.cooking_book.repository

import com.example.cooking_book.models.Recipe
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

/**
 * Репозиторий для управления основными данными рецептов.
 */
interface RecipeRepository : JpaRepository<Recipe, Int> {
    /**
     * Обновляет основные поля рецепта (название, описание, фото) по его ID.
     *
     * @param idRecipe ID обновляемого рецепта.
     * @param title Новое название.
     * @param description Новое описание.
     * @param photoUrl Новая ссылка на изображение.
     */
    @Modifying
    @Transactional
    @Query("UPDATE Recipe r SET r.title = :title, r.description = :description, r.photoUrl = :photoUrl " +
            "WHERE r.idRecipe = :idRecipe")
    fun updateRecipeById(
        @Param("idRecipe") idRecipe: Long,
        @Param("title") title: String,
        @Param("description") description: String,
        @Param("photoUrl") photoUrl: String
    )
}