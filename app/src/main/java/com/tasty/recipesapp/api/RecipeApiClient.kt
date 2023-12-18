package com.tasty.recipesapp.api

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