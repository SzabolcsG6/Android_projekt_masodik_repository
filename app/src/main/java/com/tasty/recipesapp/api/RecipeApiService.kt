package com.tasty.recipesapp.api

import androidx.room.Query
import com.tasty.recipesapp.dtos.RecipeDto

interface RecipeService {
   @GET("recipes/list")
   @Headers(
       'X-RapidAPI-Key': '1b26550e02msh5d717671bfa917fp111414jsncaf0675cfb97',
    'X-RapidAPI-Host': 'tasty.p.rapidapi.com'
    )
    suspend fun getRecipes(
        @Query("from") from: String,
        @Query("size") size: String,
        @Query("tags") tags: String? = null
    ) : RecipeDto
}