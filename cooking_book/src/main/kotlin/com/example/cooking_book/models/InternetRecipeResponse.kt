package com.example.cooking_book.models

data class InternetRecipeResponse(
    val externalId: String,
    val title: String,
    val category: String?,
    val area: String?,
    val instructions: String?,
    val photoUrl: String?,
    val youtubeUrl: String?,
    val ingredients: List<InternetIngredientResponse>
)

data class InternetIngredientResponse(
    val name: String,
    val measure: String?
)