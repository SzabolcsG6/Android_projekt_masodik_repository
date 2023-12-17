package com.tasty.recipesapp.models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.RecipeEntity
import com.tasty.recipesapp.dtos.Recipe
import com.tasty.recipesapp.dtos.RecipeDto
import com.tasty.recipesapp.repositories.RecipeRepository
import kotlinx.coroutines.launch
import java.io.IOException
data class RecipeModel(
    val id: String,
    val name: String,
    val imageUrl: String,
    val thumbnailUrl: String?,
    val promotion: String?,
    val originalVideoUrl: String?,
    val servingsNounPlural: String?,
    val videoAdContent: String?,
    val seoTitle: String?,
    val seoPath: String?,
    val canonicalId: String?,
    val beautyUrl: String?,
    val draftStatus: String?,
    val aspectRatio: String?,
    val difficultyLevel: String?,
    val cuisineType: String?,
    val dietaryInformation: String?,
    val mealType: String?,
    val calories: Int?,
    val nutritionalInfo: String?,
    val allergens: String?
)


class RecipeViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {
    private val _recipeList = MutableLiveData<List<RecipeDto>>()
    val recipeList: LiveData<List<RecipeDto>> get() = _recipeList

    fun loadRecipes(context: Context) {
        viewModelScope.launch {
            try {
                val recipes = recipeRepository.loadRecipesFromJson()
                _recipeList.value = recipes
            } catch (e: IOException) {
                // Handle file loading error: Display error message or return default value
                e.printStackTrace()
                // For example:
                _recipeList.value = emptyList()
            }
        }
    }


    fun filterRecipesById(id: String) {
        val currentRecipes = _recipeList.value.orEmpty()
        val filteredRecipes = currentRecipes.filter { it.id == id }
        _recipeList.value = filteredRecipes
    }
    fun filterRecipesByName(name: String) {
        val currentRecipes = _recipeList.value.orEmpty()
        val filteredRecipes = currentRecipes.filter { it.name == name }
        _recipeList.value = filteredRecipes
    }

}

