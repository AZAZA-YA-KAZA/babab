package com.example.cooking_book.service


import com.example.cooking_book.models.*
import com.example.cooking_book.repository.IngredientRepository
import com.example.cooking_book.repository.RecipeIngredientsRepository
import com.example.cooking_book.repository.RecipeRepository
import com.example.cooking_book.repository.StepRepository
import org.springframework.stereotype.Service

@Service
class ProjectService(
    private val recipeRepository: RecipeRepository,
    private val stepRepository: StepRepository,
    private val ingredientRepository: IngredientRepository,
    private val recipeIngredientsRepository: RecipeIngredientsRepository
) {
    fun createRecipe(recipeRequest: RecipeRequest): Recipe {
        val recipe = Recipe(
            title = recipeRequest.title,
            description = recipeRequest.description,
            photoUrl = recipeRequest.photoUrl
        )
        recipeRepository.save(recipe)
        return recipe
    }

    fun createStep(idRecipe: Long, stepRequest: StepRequest): Step {
        val step = Step(
            idRecipe = idRecipe,
            stepDescription = stepRequest.stepDescription,
            stepOrder = stepRequest.stepOrder
        )
        stepRepository.save(step)
        return step
    }

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

    fun updateStep(idStep: Long, stepRequest: StepRequest): Step {
        stepRepository.updateStepById(
            idStep = idStep,
            stepDescription = stepRequest.stepDescription,
            stepOrder = stepRequest.stepOrder
        )
        return stepRepository.findById(idStep.toInt())
            .orElseThrow { Exception("Step not found") }
    }

    fun deleteRecipe(idRecipe: Long): String {
        recipeRepository.deleteById(idRecipe.toInt())
        return "Deleted"
    }

    fun deleteStep(idRecipe: Long, idStep: Long): String {
        stepRepository.deleteById(idRecipe, idStep)
        return "Deleted"
    }

    fun deleteIngredient(idRecipe: Long, idIngredient: Long): String {
        recipeIngredientsRepository.deleteById(idRecipe, idIngredient)
        return "Deleted"
    }

    fun getRecipe(idRecipe: Long): Recipe {
        return recipeRepository.findById(idRecipe.toInt())
            .orElseThrow { Exception("Recipe not found") }
    }

    fun getStep(idRecipe: Long): List<Step> {
        return stepRepository.getListStepByRecipe(idRecipe)
    }

    fun getIngredient(idRecipe: Long): List<Array<Any>> {
        return recipeIngredientsRepository.getListIngredientByRecipe(idRecipe)
    }

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


    fun getAllRecipes(): List<Recipe> {
        return recipeRepository.findAllRec()
    }

}