package com.tasty.recipesapp.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.api.RecipeApiClient
import com.tasty.recipesapp.repository.recipe.RecipeDatabase
import com.tasty.recipesapp.repository.recipe.enitity.RecipeDAO
import com.tasty.recipesapp.repository.recipe.enitity.RecipeEntity
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.repository.recipe.model.RecipesDTO
import com.tasty.recipesapp.repository.recipe.model.toModelList
import java.io.IOException

object RecipeRepository {
    private lateinit var recipeDao: RecipeDAO
    private lateinit var recipeDatabase: RecipeDatabase // New addition
    private val TAG: String? = RecipeRepository::class.java.canonicalName
    private var recipesList: List<RecipeModel> = emptyList()
    private var myRecipesList: ArrayList<RecipeModel> = ArrayList()

    private val recipeApiClient = RecipeApiClient()
    fun initialize(recipeDatabase: RecipeDatabase) {
        this.recipeDatabase = recipeDatabase
        this.recipeDao = recipeDatabase.recipeDao()
    }
    suspend fun getRecipeFromApi(
        from: String,
        size: String,
        tags: String? = null
    ): List<RecipeModel> {
        recipesList = recipeApiClient.recipeService.getRecipes(from, size, tags).results.toModelList()
        return recipesList
    }
    suspend fun searchRecipesFromApi(
        from: String,
        size: String,
        tags: String? = null,
        query: String
    ): List<RecipeModel> {
        recipesList = recipeApiClient.recipeService.getRecipes(from, size, tags).results
            .filter { it.name.contains(query, ignoreCase = true) }
            .toModelList()
        return recipesList
    }

    fun getRecipes(context: Context): List<RecipeModel> {
        lateinit var jsonString: String

        try {
            jsonString =
                context.assets.open("all_recipes.json")
                    .bufferedReader()
                    .use {
                        it.readText()
                    }
        } catch (ioException: IOException) {
            Log.e(TAG, "Error occurred while reading JSON file: $ioException")
        }

        val recipesResponse: RecipesDTO =
            Gson().fromJson(jsonString, object : TypeToken<RecipesDTO>() {}.type)

        recipesList = recipesResponse.results.toModelList()
        return recipesList

    }


    fun getRecipe(recipeId: Int): RecipeModel? {
        return recipesList.find { it.id == recipeId }
    }


    fun insertRecipe(recipeModel: RecipeModel) = myRecipesList.add(recipeModel)
    suspend fun insertRecipeDatabase(recipe: RecipeEntity) {
        recipeDao.insertRecipe(recipe)
        Log.d("Database-", "Inserted")
    }
    fun deleteRecipe(recipeModel: RecipeModel) = myRecipesList.remove(recipeModel)
    fun getMyRecipes() = myRecipesList
    fun getRecipesSortedByRating(): List<RecipeModel> {
        return recipesList.sortedByDescending { it.userRatings.score }
    }
    fun getRecipesSortedByName(): List<RecipeModel> {
        return recipesList.sortedByDescending { it.name }
    }

}