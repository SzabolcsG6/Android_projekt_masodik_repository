package com.tasty.recipesapp.dtos

import com.tasty.recipesapp.models.RecipeModel

data class RecipesDto (
    val count: Int,
    val results: List<RecipeDto>
)
