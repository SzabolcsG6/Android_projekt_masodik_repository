package com.tasty.recipesapp.dtos

data class RecipesDto (
    val count: Int,
    val results: List<RecipeDto>
)