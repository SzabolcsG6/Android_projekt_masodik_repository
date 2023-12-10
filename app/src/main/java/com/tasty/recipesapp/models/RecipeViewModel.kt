package com.tasty.recipesapp.models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class RecipeViewModel : ViewModel() {
//    private val _recipeList = MutableLiveData<List<Recipe>>() // Define LiveData for recipes
//    val recipeList: LiveData<List<Recipe>> get() = _recipeList // Expose LiveData
//
//    fun readAllRecipes(context: Context) {
//        val jsonString = loadJsonFromAssets(context, "all_recipes.json") // Load JSON file
//        val recipeList = parseJsonToRecipes(jsonString) // Parse JSON to list of Recipe objects
//        _recipeList.value = recipeList // Set the value of LiveData
//    }
//
//    private fun loadJsonFromAssets(context: Context, fileName: String): String {
//        return try {
//            val inputStream = context.assets.open(fileName)
//            val size = inputStream.available()
//            val buffer = ByteArray(size)
//            inputStream.read(buffer)
//            inputStream.close()
//            String(buffer)
//        } catch (ioException: IOException) {
//            ioException.printStackTrace()
//            ""
//        }
//    }
//
//    private fun parseJsonToRecipes(jsonString: String): List<Recipe> {
//        val gson = Gson()
//        val listType = object : TypeToken<List<Recipe>>() {}.type
//        return gson.fromJson(jsonString, listType)
//    }
//
//    // Filter recipes based on the provided type
//    fun filterRecipesByType(type: String) {
//        val currentRecipes = _recipeList.value.orEmpty()
//        val filteredRecipes = currentRecipes.filter { it.type == type }
//        _recipeList.value = filteredRecipes
//    }
}
