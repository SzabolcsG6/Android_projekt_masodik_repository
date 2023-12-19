package com.tasty.recipesapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeApiClient {
    companion object {
        private const val BASE_URL = "https://tasty.p.rapidapi.com/"
    }

    val recipeService: RecipeService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        recipeService = retrofit.create(RecipeService::class.java)
    }

}