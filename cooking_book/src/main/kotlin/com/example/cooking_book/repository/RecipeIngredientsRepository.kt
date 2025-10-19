package com.example.kulinarya.repository

import com.example.kulinarya.models.RecipesIngredients
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface RecipeIngredientsRepository : JpaRepository<RecipesIngredients, Int> {
    @Modifying
    @Transactional
    @Query("DELETE FROM RecipesIngredients r WHERE r.idRecipe = :idRecipe AND r.idIngredient = :idIngredient")
    fun deleteById(@Param("idRecipe") idRecipe: Long, @Param("idIngredient") idIngredient: Long)

    @Query("SELECT ri.count, ri.unit, i.name FROM RecipesIngredients ri " +
            "JOIN Ingredient i ON i.idIngredient = ri.idIngredient " +
            "WHERE ri.idRecipe = :idRecipe")
    fun getListIngredientByRecipe(@Param("idRecipe") idRecipe: Long): List<Array<Any>>

    @Modifying
    @Transactional
    @Query("UPDATE RecipesIngredients ri SET ri.count = :count, ri.unit = :unit " +
            "WHERE ri.idIngredient = :idIngredient AND ri.idRecipe = :idRecipe")
    fun updateIngredientById(
        @Param("idRecipe") idRecipe: Long,
        @Param("idIngredient") idIngredient: Long,
        @Param("count") count: Int,
        @Param("unit") unit: String
    )

    @Query("SELECT ri FROM RecipesIngredients ri WHERE ri.idIngredient = :idIngredient AND ri.idRecipe = :idRecipe")
    fun getIngredientRecipe(@Param("idRecipe") idRecipe: Long, @Param("idIngredient") idIngredient: Long): RecipesIngredients?
}