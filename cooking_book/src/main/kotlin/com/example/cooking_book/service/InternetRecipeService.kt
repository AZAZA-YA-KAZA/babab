package com.example.cooking_book.service

import com.example.cooking_book.models.InternetIngredientResponse
import com.example.cooking_book.models.InternetRecipeResponse
import com.example.cooking_book.models.TheMealDbResponse
import com.example.cooking_book.models.TheMealDto
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class InternetRecipeService {

    private val webClient = WebClient.builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1")
        .build()

    fun searchRecipe(query: String): List<InternetRecipeResponse> {
        val response = webClient.get()
            .uri { uriBuilder ->
                uriBuilder
                    .path("/search.php")
                    .queryParam("s", query)
                    .build()
            }
            .retrieve()
            .bodyToMono(TheMealDbResponse::class.java)
            .block()

        val meals = response?.meals ?: return emptyList()

        return meals.map { meal ->
            InternetRecipeResponse(
                externalId = meal.idMeal ?: "",
                title = meal.strMeal ?: "",
                category = meal.strCategory,
                area = meal.strArea,
                instructions = meal.strInstructions,
                photoUrl = meal.strMealThumb,
                youtubeUrl = meal.strYoutube,
                ingredients = extractIngredients(meal)
            )
        }
    }

    private fun extractIngredients(meal: TheMealDto): List<InternetIngredientResponse> {
        val ingredients = listOf(
            meal.strIngredient1 to meal.strMeasure1,
            meal.strIngredient2 to meal.strMeasure2,
            meal.strIngredient3 to meal.strMeasure3,
            meal.strIngredient4 to meal.strMeasure4,
            meal.strIngredient5 to meal.strMeasure5,
            meal.strIngredient6 to meal.strMeasure6,
            meal.strIngredient7 to meal.strMeasure7,
            meal.strIngredient8 to meal.strMeasure8,
            meal.strIngredient9 to meal.strMeasure9,
            meal.strIngredient10 to meal.strMeasure10,
            meal.strIngredient11 to meal.strMeasure11,
            meal.strIngredient12 to meal.strMeasure12,
            meal.strIngredient13 to meal.strMeasure13,
            meal.strIngredient14 to meal.strMeasure14,
            meal.strIngredient15 to meal.strMeasure15,
            meal.strIngredient16 to meal.strMeasure16,
            meal.strIngredient17 to meal.strMeasure17,
            meal.strIngredient18 to meal.strMeasure18,
            meal.strIngredient19 to meal.strMeasure19,
            meal.strIngredient20 to meal.strMeasure20
        )

        return ingredients
            .filter { !it.first.isNullOrBlank() }
            .map {
                InternetIngredientResponse(
                    name = it.first!!.trim(),
                    measure = it.second?.trim()
                )
            }
    }
}