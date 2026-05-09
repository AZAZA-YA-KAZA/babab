package com.example.cooking_book.controllers

import com.example.cooking_book.models.Ingredient
import com.example.cooking_book.models.IngredientRequest
import com.example.cooking_book.models.Recipe
import com.example.cooking_book.models.RecipeRequest
import com.example.cooking_book.service.ProjectService
import com.example.cooking_book.models.RecipesIngredients
import com.example.cooking_book.models.Step
import com.example.cooking_book.models.StepRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class Controller(private val projectService: ProjectService) {

    // Создание рецепта
    @PostMapping("/api/kulinare/recipe")
    fun createRecipe(@RequestBody recipeRequest: RecipeRequest): ResponseEntity<Recipe> {
        return ResponseEntity.ok(projectService.createRecipe(recipeRequest))
    }

    // Создание шага
    @PostMapping("/api/kulinare/{idRecipe}/step")
    fun createStep(
        @PathVariable idRecipe: Long,
        @RequestBody stepRequest: StepRequest
    ): ResponseEntity<Step> {
        return ResponseEntity.ok(projectService.createStep(idRecipe, stepRequest))
    }

    // Добавление ингредиента
    @PostMapping("/api/kulinare/{idRecipe}/ingredient")
    fun addIngredient(
        @PathVariable idRecipe: Long,
        @RequestBody ingredientRequest: IngredientRequest
    ): ResponseEntity<Ingredient> {
        return ResponseEntity.ok(projectService.addIngredient(idRecipe, ingredientRequest))
    }

    // Редактирование рецепта
    @PutMapping("/api/kulinare/recipe/{idRecipe}")
    fun updateRecipe(
        @PathVariable idRecipe: Long,
        @RequestBody recipeRequest: RecipeRequest
    ): ResponseEntity<Recipe> {
        return ResponseEntity.ok(projectService.updateRecipe(idRecipe, recipeRequest))
    }

    // Редактирование шага в рецепте
    @PutMapping("/api/kulinare/step/{idStep}")
    fun updateStep(
        @PathVariable idStep: Long,
        @RequestBody stepRequest: StepRequest
    ): ResponseEntity<Step> {
        return ResponseEntity.ok(projectService.updateStep(idStep, stepRequest))
    }

    // Редактирование ингредиента в рецепте
    @PutMapping("/api/kulinare/ingredient/{idRecipe}/{idIngredient}")
    fun updateIngredient(
        @PathVariable idRecipe: Long,
        @PathVariable idIngredient: Long,
        @RequestBody ingredientRequest: IngredientRequest
    ): ResponseEntity<RecipesIngredients> {
        return ResponseEntity.ok(projectService.updateIngredient(idRecipe, idIngredient, ingredientRequest))
    }

    // Удаление рецепта
    @DeleteMapping("/api/kulinare/recipe/{idRecipe}")
    fun deleteRecipe(@PathVariable idRecipe: Long): ResponseEntity<String> {
        return ResponseEntity.ok(projectService.deleteRecipe(idRecipe))
    }

    // Удаление шага в рецепте
    @DeleteMapping("/api/kulinare/step/{idRecipe}/{idStep}")
    fun deleteStep(
        @PathVariable idRecipe: Long,
        @PathVariable idStep: Long
    ): ResponseEntity<String> {
        return ResponseEntity.ok(projectService.deleteStep(idRecipe, idStep))
    }

    // Удаление ингредиента в рецепте
    @DeleteMapping("/api/kulinare/{idRecipe}/{idIngredient}")
    fun deleteIngredient(
        @PathVariable idRecipe: Long,
        @PathVariable idIngredient: Long
    ): ResponseEntity<String> {
        return ResponseEntity.ok(projectService.deleteIngredient(idRecipe, idIngredient))
    }

    // Выдача рецепта
    @GetMapping("/api/kulinare/recipe/{idRecipe}")
    fun getRecipe(@PathVariable idRecipe: Long): ResponseEntity<Recipe> {
        return ResponseEntity.ok(projectService.getRecipe(idRecipe))
    }

    // Выдача списка шагов рецепта
    @GetMapping("/api/kulinare/{idRecipe}/step")
    fun getStep(@PathVariable idRecipe: Long): ResponseEntity<List<Step>> {
        return ResponseEntity.ok(projectService.getStep(idRecipe))
    }

    // Выдача списка ингредиентов рецепта
    @GetMapping("/api/kulinare/{idRecipe}/ingredient")
    fun getIngredient(@PathVariable idRecipe: Long): ResponseEntity<List<Array<Any>>> {
        return ResponseEntity.ok(projectService.getIngredient(idRecipe))
    }


    @GetMapping("/api/kulinare/recipes")
    fun getAllRecipes(): ResponseEntity<List<Recipe>> {
        return ResponseEntity.ok(projectService.getAllRecipes())
    }
}