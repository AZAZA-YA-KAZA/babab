package com.example.cooking_book.service

import com.example.cooking_book.models.*
import com.example.cooking_book.repository.IngredientRepository
import com.example.cooking_book.repository.RecipeIngredientsRepository
import com.example.cooking_book.repository.RecipeRepository
import com.example.cooking_book.repository.StepRepository
import org.springframework.stereotype.Service

/**
 * Сервис для управления бизнес-логикой кулинарной книги.
 * * Осуществляет координацию между репозиториями для выполнения операций с рецептами,
 * шагами приготовления и ингредиентами.
 *
 * @property recipeRepository Репозиторий для работы с основной информацией о рецептах.
 * @property stepRepository Репозиторий для управления пошаговыми инструкциями.
 * @property ingredientRepository Репозиторий для справочника ингредиентов.
 * @property recipeIngredientsRepository Репозиторий для связи рецептов с их составом.
 */
@Service
class ProjectService(
    private val recipeRepository: RecipeRepository,
    private val stepRepository: StepRepository,
    private val ingredientRepository: IngredientRepository,
    private val recipeIngredientsRepository: RecipeIngredientsRepository
) {
    /**
     * Создает и сохраняет новый рецепт.
     * * @param recipeRequest Данные нового рецепта.
     * @return Сохраненная сущность [Recipe] с присвоенным ID.
     */
    fun createRecipe(recipeRequest: RecipeRequest): Recipe {
        val recipe = Recipe(
            title = recipeRequest.title,
            description = recipeRequest.description,
            photoUrl = recipeRequest.photoUrl
        )
        recipeRepository.save(recipe)
        return recipe
    }

    /**
     * Создает новый шаг приготовления для указанного рецепта.
     * * @param idRecipe Идентификатор рецепта.
     * @param stepRequest Содержание шага и его порядковый номер.
     * @return Сохраненная сущность [Step].
     */
    fun createStep(idRecipe: Long, stepRequest: StepRequest): Step {
        val step = Step(
            idRecipe = idRecipe,
            stepDescription = stepRequest.stepDescription,
            stepOrder = stepRequest.stepOrder
        )
        stepRepository.save(step)
        return step
    }

    /**
     * Добавляет ингредиент к рецепту.
     * Если ингредиент с таким названием отсутствует в справочнике, он создается автоматически.
     * Затем создается запись о связи рецепта с этим ингредиентом (количество и единицы измерения).
     * * @param idRecipe Идентификатор рецепта.
     * @param ingredientRequest Данные ингредиента (название, кол-во, единицы).
     * @return Сущность [Ingredient] (существующая или вновь созданная).
     * @throws Exception Если ингредиент не найден после попытки поиска или создания.
     */
    fun addIngredient(idRecipe: Long, ingredientRequest: IngredientRequest): Ingredient {
        val idIngredient = ingredientRepository.getIdByName(ingredientRequest.name)
        val ingredient = if (idIngredient == null) {
            val newIngredient = Ingredient(ingredientRequest.name)
            ingredientRepository.save(newIngredient)
            newIngredient
        } else {
            ingredientRepository.findById(idIngredient.toInt())
                .orElseThrow { Exception("Ingredient not found") }
        }

        val recipesIngredients = RecipesIngredients(
            idRecipe = idRecipe,
            idIngredient = ingredient.idIngredient ?: throw Exception("Ingredient ID is null"),
            count = ingredientRequest.count,
            unit = ingredientRequest.unit
        )
        recipeIngredientsRepository.save(recipesIngredients)
        return ingredient
    }

    /**
     * Обновляет основные данные рецепта.
     * * @param idRecipe Идентификатор рецепта.
     * @param recipeRequest Новые данные для обновления.
     * @return Обновленная сущность [Recipe].
     * @throws Exception Если рецепт с таким ID не существует.
     */
    fun updateRecipe(idRecipe: Long, recipeRequest: RecipeRequest): Recipe {
        recipeRepository.updateRecipeById(
            idRecipe = idRecipe,
            title = recipeRequest.title,
            description = recipeRequest.description,
            photoUrl = recipeRequest.photoUrl
        )
        return recipeRepository.findById(idRecipe.toInt())
            .orElseThrow { Exception("Recipe not found") }
    }

    /**
     * Обновляет описание или порядок конкретного шага.
     * * @param idStep Идентификатор шага.
     * @param stepRequest Новые данные шага.
     * @return Обновленная сущность [Step].
     * @throws Exception Если шаг с таким ID не найден.
     */
    fun updateStep(idStep: Long, stepRequest: StepRequest): Step {
        stepRepository.updateStepById(
            idStep = idStep,
            stepDescription = stepRequest.stepDescription,
            stepOrder = stepRequest.stepOrder
        )
        return stepRepository.findById(idStep.toInt())
            .orElseThrow { Exception("Step not found") }
    }

    /**
     * Безвозвратно удаляет рецепт из базы данных.
     * * @param idRecipe Идентификатор рецепта.
     * @return Строка-подтверждение "Deleted".
     */
    fun deleteRecipe(idRecipe: Long): String {
        recipeRepository.deleteById(idRecipe.toInt())
        return "Deleted"
    }

    /**
     * Удаляет конкретный шаг из рецепта.
     * * @param idRecipe Идентификатор рецепта.
     * @param idStep Идентификатор шага.
     * @return Строка-подтверждение "Deleted".
     */
    fun deleteStep(idRecipe: Long, idStep: Long): String {
        stepRepository.deleteById(idRecipe, idStep)
        return "Deleted"
    }

    /**
     * Удаляет ингредиент из состава конкретного рецепта.
     * Обратите внимание: сам ингредиент остается в общем справочнике.
     * * @param idRecipe Идентификатор рецепта.
     * @param idIngredient Идентификатор ингредиента.
     * @return Строка-подтверждение "Deleted".
     */
    fun deleteIngredient(idRecipe: Long, idIngredient: Long): String {
        recipeIngredientsRepository.deleteById(idRecipe, idIngredient)
        return "Deleted"
    }

    /**
     * Возвращает информацию о конкретном рецепте.
     * * @param idRecipe Идентификатор рецепта.
     * @return Сущность [Recipe].
     * @throws Exception Если рецепт не найден.
     */
    fun getRecipe(idRecipe: Long): Recipe {
        return recipeRepository.findById(idRecipe.toInt())
            .orElseThrow { Exception("Recipe not found") }
    }

    /**
     * Возвращает список всех шагов приготовления для указанного рецепта.
     * * @param idRecipe Идентификатор рецепта.
     * @return Список объектов [Step].
     */
    fun getStep(idRecipe: Long): List<Step> {
        return stepRepository.getListStepByRecipe(idRecipe)
    }

    /**
     * Возвращает список ингредиентов рецепта с их количеством и названиями.
     * * @param idRecipe Идентификатор рецепта.
     * @return Список массивов данных (количество, единица измерения, название).
     */
    fun getIngredient(idRecipe: Long): List<Array<Any>> {
        return recipeIngredientsRepository.getListIngredientByRecipe(idRecipe)
    }

    /**
     * Обновляет пропорции (количество и единицы измерения) ингредиента в рецепте.
     * * @param idRecipe Идентификатор рецепта.
     * @param idIngredient Идентификатор ингредиента.
     * @param ingredientRequest Новые данные о количестве и единицах.
     * @return Обновленная запись связи [RecipesIngredients].
     * @throws Exception Если запись о связи не найдена.
     */
    fun updateIngredient(idRecipe: Long, idIngredient: Long, ingredientRequest: IngredientRequest): RecipesIngredients {
        recipeIngredientsRepository.updateIngredientById(
            idRecipe = idRecipe,
            idIngredient = idIngredient,
            count = ingredientRequest.count,
            unit = ingredientRequest.unit
        )
        return recipeIngredientsRepository.getIngredientRecipe(idRecipe, idIngredient)
            ?: throw Exception("RecipeIngredient not found")
    }
}