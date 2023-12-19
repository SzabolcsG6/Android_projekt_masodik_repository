package com.tasty.recipesapp.api

import com.tasty.recipesapp.dtos.RecipeDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
interface RecipeService {
   @GET("recipes/list")
   @Headers(
       "X-RapidAPI-Key': '1b26550e02msh5d717671bfa917fp111414jsncaf0675cfb97",
    "X-RapidAPI-Host': 'tasty.p.rapidapi.com"
    )
    suspend fun getRecipes(
        @retrofit2.http.Query("from") from: String,
        @retrofit2.http.Query("size") size: String,
        @retrofit2.http.Query("tags") tags: String? = null
    ) : RecipeDto
}