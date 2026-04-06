package com.example.cooking_book.repository

import com.example.cooking_book.models.RecipesIngredients
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

/**
 * Репозиторий для управления связями между рецептами и ингредиентами.
 * Обрабатывает количественные данные продуктов для каждого конкретного блюда.
 */
interface RecipeIngredientsRepository : JpaRepository<RecipesIngredients, Int> {
    /**
     * Удаляет связь ингредиента с рецептом по их идентификаторам.
     *
     * @param idRecipe ID рецепта.
     * @param idIngredient ID ингредиента.
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM RecipesIngredients r WHERE r.idRecipe = :idRecipe AND r.idIngredient = :idIngredient")
    fun deleteById(@Param("idRecipe") idRecipe: Long, @Param("idIngredient") idIngredient: Long)

    /**
     * Получает детализированный список ингредиентов для конкретного рецепта.
     * Выполняет JOIN с таблицей Ingredient для получения названий.
     *
     * @param idRecipe ID рецепта.
     * @return Список массивов данных: [количество, единица измерения, название ингредиента].
     */
    @Query("SELECT ri.count, ri.unit, i.name FROM RecipesIngredients ri " +
            "JOIN Ingredient i ON i.idIngredient = ri.idIngredient " +
            "WHERE ri.idRecipe = :idRecipe")
    fun getListIngredientByRecipe(@Param("idRecipe") idRecipe: Long): List<Array<Any>>

    /**
     * Обновляет количество и единицы измерения для ингредиента в конкретном рецепте.
     *
     * @param idRecipe ID рецепта.
     * @param idIngredient ID ингредиента.
     * @param count Новое количество.
     * @param unit Новая единица измерения.
     */
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

    /**
     * Находит запись о связи рецепта и ингредиента.
     *
     * @param idRecipe ID рецепта.
     * @param idIngredient ID ингредиента.
     * @return Объект [RecipesIngredients] или null, если связь не найдена.
     */
    @Query("SELECT ri FROM RecipesIngredients ri WHERE ri.idIngredient = :idIngredient AND ri.idRecipe = :idRecipe")
    fun getIngredientRecipe(@Param("idRecipe") idRecipe: Long, @Param("idIngredient") idIngredient: Long): RecipesIngredients?
}