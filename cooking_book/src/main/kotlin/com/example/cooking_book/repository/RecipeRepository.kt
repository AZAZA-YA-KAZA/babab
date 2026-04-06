package com.example.cooking_book.repository


import com.example.cooking_book.models.Recipe
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface RecipeRepository : JpaRepository<Recipe, Int> {
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

    @Query("SELECT r FROM Recipe r")
    fun findAllRec(): List<Recipe>}