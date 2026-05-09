package com.example.cooking_book.repository

import com.example.cooking_book.models.Ingredient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface IngredientRepository : JpaRepository<Ingredient, Int> {
    @Query("SELECT idIngredient FROM Ingredient WHERE name = :name")
    fun getIdByName(@Param("name") name: String): Long?
}