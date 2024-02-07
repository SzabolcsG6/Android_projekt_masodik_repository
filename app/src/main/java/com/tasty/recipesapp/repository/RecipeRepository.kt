package com.tasty.recipesapp.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.api.RecipeApiClient
import com.tasty.recipesapp.repository.recipe.RecipeDatabase
import com.tasty.recipesapp.repository.recipe.enitity.RecipeDAO
import com.tasty.recipesapp.repository.recipe.enitity.RecipeEntity
import com.tasty.recipesapp.repository.recipe.enitity.toRecipeModel
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.repository.recipe.model.RecipesDTO
import com.tasty.recipesapp.repository.recipe.model.toModelList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

object RecipeRepository {
    private lateinit var recipeDao: RecipeDAO
    private lateinit var recipeDatabase: RecipeDatabase // New addition
    private val TAG: String? = RecipeRepository::class.java.canonicalName
    private var recipesList: List<RecipeModel> = emptyList()
    private var dbRecipesList: List<RecipeModel> = emptyList()
     //private var myRecipesList: ArrayList<RecipeModel> = ArrayList()
    private lateinit var recipesListLiveData: LiveData<List<RecipeEntity>>
    private val recipeApiClient = RecipeApiClient()
//database
    //private val _allRecipes = MutableLiveData<List<RecipeEntity>>()
   // val allRecipes: LiveData<List<RecipeEntity>> = _allRecipes



    fun initialize(recipeDatabase: RecipeDatabase) {
        this.recipeDatabase = recipeDatabase
        this.recipeDao = recipeDatabase.recipeDao()
        recipesListLiveData = recipeDao.getAllRecipesLiveData()

        // Observe changes in recipes list
        recipesListLiveData.observeForever { allRecipes ->
            // You can access allRecipes on the background thread
            //viewModelScope.launch {
                // Update the recipes list asynchronously
                dbRecipesList = allRecipes.map { it.toRecipeModel() } // Convert RecipeEntity to RecipeModel if needed
            }
        //}
    }


    suspend fun getRecipeFromApi(
        from: String,
        size: String,
        tags: String? = null
    ): List<RecipeModel> {
        recipesList = recipeApiClient.recipeService.getRecipes(from, size, tags).results.toModelList()
        return recipesList
    }

    fun getAllRecipesFromDatabase(): LiveData<List<RecipeEntity>> {
        return recipeDao.getAllRecipesLiveData()
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
    fun getRecipesFromFile(context: Context): List<RecipeModel> {
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
    suspend fun deleteRecipe(recipe: RecipeModel) {
        withContext(Dispatchers.IO) {
            val json = Gson().toJson(recipe)
            val recipeEntity = RecipeEntity(json = json)
            recipeDao.deleteRecipe(recipeEntity)
        }
    }






    fun getRecipe(recipeId: Int): RecipeModel? {
        return recipesList.find { it.id == recipeId }
    }
    suspend fun insertRecipeDatabase(recipe: RecipeEntity) {
        withContext(Dispatchers.IO) {
        recipeDao.insertRecipe(recipe)
        Log.d("Database-", "Inserted")}
    }
//    suspend fun deleteRecipe(recipe: RecipeEntity) {
//        recipeDao.deleteRecipe(recipe)
//        Log.d("Recipe-", "Deleted")
//    }
    //fun getMyRecipes() = myRecipesList
    fun getRecipesSortedByRating(): List<RecipeModel> {
        return recipesList.sortedByDescending { it.userRatings.score }
    }

    fun getRecipesSortedByRatingAscending(): List<RecipeModel> {
        return recipesList.sortedBy { it.userRatings.score }
    }

    fun getRecipesSortedByName(): List<RecipeModel> {
        return recipesList.sortedByDescending { it.name }
    }
    fun getRecipesSortedByNameAscending(): List<RecipeModel> {
        return recipesList.sortedBy { it.name }
    }

//    fun getRecipesSortedByRatingDatabase(): List<RecipeModel> {
//        return myRecipesList.sortedByDescending { it.userRatings.score }
//    }
//    fun getRecipesSortedByRatingAscendingDatabase(): List<RecipeModel> {
//        return myRecipesList.sortedBy { it.userRatings.score }
//    }
//
//    fun getRecipesSortedByNameDatabase(): List<RecipeModel> {
//        return myRecipesList.sortedByDescending { it.name }
//    }
//    fun getRecipesSortedByNameAscendingDatabase(): List<RecipeModel> {
//        return myRecipesList.sortedBy { it.name }
//    }


}