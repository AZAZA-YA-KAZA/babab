package com.example.cooking_book.controllers

import com.example.cooking_book.models.*
import com.example.cooking_book.service.ProjectService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * REST-контроллер для управления системой «Кулинарная книга».
 *
 * Обеспечивает API для работы с рецептами, их ингредиентами и шагами приготовления.
 * Реализует функциональные возможности по созданию, редактированию, удалению
 * и получению данных о блюдах.
 *
 * @property projectService Сервис, содержащий бизнес-логику обработки данных рецептов.
 */
@RestController
class Controller(private val projectService: ProjectService) {

    /**
     * Создает новый рецепт в личной книге пользователя.
     *
     * @param recipeRequest Объект с данными рецепта (название, описание, время, порции, заметки).
     * @return [ResponseEntity] с созданным объектом [Recipe].
     */
    @PostMapping("/api/kulinare/recipe")
    fun createRecipe(@RequestBody recipeRequest: RecipeRequest): ResponseEntity<Recipe> {
        return ResponseEntity.ok(projectService.createRecipe(recipeRequest))
    }

    /**
     * Добавляет новый шаг приготовления к конкретному рецепту.
     * Позволяет сделать инструкцию более подробной.
     *
     * @param idRecipe Идентификатор рецепта, в который добавляется шаг.
     * @param stepRequest Данные шага (порядковый номер, текст, время этапа).
     * @return [ResponseEntity] с созданным объектом [Step].
     */
    @PostMapping("/api/kulinare/{idRecipe}/step")
    fun createStep(
        @PathVariable idRecipe: Long,
        @RequestBody stepRequest: StepRequest
    ): ResponseEntity<Step> {
        return ResponseEntity.ok(projectService.createStep(idRecipe, stepRequest))
    }

    /**
     * Добавляет ингредиент в список продуктов рецепта.
     *
     * @param idRecipe Идентификатор рецепта.
     * @param ingredientRequest Данные ингредиента (название, количество, единица измерения, комментарий).
     * @return [ResponseEntity] с созданным объектом [Ingredient].
     */
    @PostMapping("/api/kulinare/{idRecipe}/ingredient")
    fun addIngredient(
        @PathVariable idRecipe: Long,
        @RequestBody ingredientRequest: IngredientRequest
    ): ResponseEntity<Ingredient> {
        return ResponseEntity.ok(projectService.addIngredient(idRecipe, ingredientRequest))
    }

    /**
     * Обновляет основные данные существующего рецепта (актуализация данных).
     *
     * @param idRecipe Идентификатор редактируемого рецепта.
     * @param recipeRequest Новые данные для полей названия, описания, времени и порций.
     * @return [ResponseEntity] с обновленным объектом [Recipe].
     */
    @PutMapping("/api/kulinare/recipe/{idRecipe}")
    fun updateRecipe(
        @PathVariable idRecipe: Long,
        @RequestBody recipeRequest: RecipeRequest
    ): ResponseEntity<Recipe> {
        return ResponseEntity.ok(projectService.updateRecipe(idRecipe, recipeRequest))
    }

    /**
     * Редактирует текст или порядок шага в рецепте.
     *
     * @param idStep Идентификатор изменяемого шага.
     * @param stepRequest Новые данные шага.
     * @return [ResponseEntity] с обновленным объектом [Step].
     */
    @PutMapping("/api/kulinare/step/{idStep}")
    fun updateStep(
        @PathVariable idStep: Long,
        @RequestBody stepRequest: StepRequest
    ): ResponseEntity<Step> {
        return ResponseEntity.ok(projectService.updateStep(idStep, stepRequest))
    }

    /**
     * Редактирует параметры ингредиента (название, количество, пропорции).
     *
     * @param idRecipe Идентификатор рецепта.
     * @param idIngredient Идентификатор ингредиента.
     * @param ingredientRequest Новые данные ингредиента.
     * @return [ResponseEntity] с обновленной связью [RecipesIngredients].
     */
    @PutMapping("/api/kulinare/ingredient/{idRecipe}/{idIngredient}")
    fun updateIngredient(
        @PathVariable idRecipe: Long,
        @PathVariable idIngredient: Long,
        @RequestBody ingredientRequest: IngredientRequest
    ): ResponseEntity<RecipesIngredients> {
        return ResponseEntity.ok(projectService.updateIngredient(idRecipe, idIngredient, ingredientRequest))
    }

    /**
     * Безвозвратно удаляет рецепт из личной книги.
     *
     * @param idRecipe Идентификатор удаляемого рецепта.
     * @return [ResponseEntity] со статусом выполнения операции.
     */
    @DeleteMapping("/api/kulinare/recipe/{idRecipe}")
    fun deleteRecipe(@PathVariable idRecipe: Long): ResponseEntity<String> {
        return ResponseEntity.ok(projectService.deleteRecipe(idRecipe))
    }

    /**
     * Удаляет конкретный шаг из последовательности приготовления.
     *
     * @param idRecipe Идентификатор рецепта.
     * @param idStep Идентификатор удаляемого шага.
     * @return [ResponseEntity] со статусом выполнения.
     */
    @DeleteMapping("/api/kulinare/step/{idRecipe}/{idStep}")
    fun deleteStep(
        @PathVariable idRecipe: Long,
        @PathVariable idStep: Long
    ): ResponseEntity<String> {
        return ResponseEntity.ok(projectService.deleteStep(idRecipe, idStep))
    }

    /**
     * Удаляет ингредиент из списка продуктов рецепта.
     *
     * @param idRecipe Идентификатор рецепта.
     * @param idIngredient Идентификатор удаляемого ингредиента.
     * @return [ResponseEntity] со статусом выполнения.
     */
    @DeleteMapping("/api/kulinare/{idRecipe}/{idIngredient}")
    fun deleteIngredient(
        @PathVariable idRecipe: Long,
        @PathVariable idIngredient: Long
    ): ResponseEntity<String> {
        return ResponseEntity.ok(projectService.deleteIngredient(idRecipe, idIngredient))
    }

    /**
     * Получает полную информацию о рецепте по его идентификатору.
     *
     * @param idRecipe Идентификатор искомого рецепта.
     * @return [ResponseEntity] с объектом [Recipe].
     */
    @GetMapping("/api/kulinare/recipe/{idRecipe}")
    fun getRecipe(@PathVariable idRecipe: Long): ResponseEntity<Recipe> {
        return ResponseEntity.ok(projectService.getRecipe(idRecipe))
    }

    /**
     * Возвращает список всех шагов приготовления для указанного рецепта.
     *
     * @param idRecipe Идентификатор рецепта.
     * @return [ResponseEntity] со списком объектов [Step].
     */
    @GetMapping("/api/kulinare/{idRecipe}/step")
    fun getStep(@PathVariable idRecipe: Long): ResponseEntity<List<Step>> {
        return ResponseEntity.ok(projectService.getStep(idRecipe))
    }

    /**
     * Возвращает полный список ингредиентов для указанного рецепта.
     *
     * @param idRecipe Идентификатор рецепта.
     * @return [ResponseEntity] со списком данных об ингредиентах.
     */
    @GetMapping("/api/kulinare/{idRecipe}/ingredient")
    fun getIngredient(@PathVariable idRecipe: Long): ResponseEntity<List<Array<Any>>> {
        return ResponseEntity.ok(projectService.getIngredient(idRecipe))
    }
}