package com.tasty.recipesapp.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import com.tasty.recipesapp.repository.recipe.model.RecipesDTO
interface RecipeService {
    @GET("recipes/list")
    @Headers(
        "X-RapidAPI-Key: 1b26550e02msh5d717671bfa917fp111414jsncaf0675cfb97",
        "X-RapidAPI-Host: tasty.p.rapidapi.com"
    )
    suspend fun getRecipes(
        @Query("from") from: String,
        @Query("size") size: String,
        @Query("tags") tags: String? = null
    ) : RecipesDTO
}