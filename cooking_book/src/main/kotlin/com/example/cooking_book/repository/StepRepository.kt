package com.example.cooking_book.repository

import com.example.cooking_book.models.Step
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

/**
 * Репозиторий для управления шагами приготовления в рецептах.
 */
interface StepRepository : JpaRepository<Step, Int> {
    /**
     * Обновляет содержание и порядок конкретного шага.
     *
     * @param idStep ID шага.
     * @param stepDescription Новый текст инструкции.
     * @param stepOrder Новая позиция в списке.
     */
    @Modifying
    @Transactional
    @Query("UPDATE Step r SET r.stepDescription = :stepDescription, r.stepOrder = :stepOrder " +
            "WHERE r.idStep = :idStep")
    fun updateStepById(
        @Param("idStep") idStep: Long,
        @Param("stepDescription") stepDescription: String,
        @Param("stepOrder") stepOrder: Int
    )

    /**
     * Удаляет конкретный шаг из рецепта.
     *
     * @param idRecipe ID рецепта.
     * @param idStep ID удаляемого шага.
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM Step s WHERE s.idRecipe = :idRecipe AND s.idStep = :idStep")
    fun deleteById(
        @Param("idRecipe") idRecipe: Long,
        @Param("idStep") idStep: Long
    )

    /**
     * Получает список всех шагов, относящихся к указанному рецепту.
     *
     * @param idRecipe ID рецепта.
     * @return Список объектов [Step], отсортированных (предполагается дальнейшая сортировка по stepOrder).
     */
    @Query("SELECT s FROM Step s WHERE s.idRecipe = :idRecipe")
    fun getListStepByRecipe(@Param("idRecipe") idRecipe: Long): List<Step>
}