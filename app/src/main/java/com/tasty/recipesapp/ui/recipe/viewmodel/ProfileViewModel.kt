package com.tasty.recipesapp.ui.recipe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.repository.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.content.Context

class ProfileViewModel(private val repository: RecipeRepository, private val context: Context) : ViewModel() {

    private val _favoriteRecipes = MutableLiveData<List<RecipeModel>>()
    val favoriteRecipes: LiveData<List<RecipeModel>> get() = _favoriteRecipes
    private val _myRecipesList: MutableLiveData<List<RecipeModel>> = MutableLiveData()
    val myRecipesList: LiveData<List<RecipeModel>> get() = _myRecipesList

    private val _deleteResult: MutableLiveData<Boolean> = MutableLiveData()
    val deleteResult: LiveData<Boolean> get() = _deleteResult

    fun fetchMyRecipesData() {
        viewModelScope.launch {
            // Use the getRecipesFromFile method to load recipes from the file
            val myRecipes = withContext(Dispatchers.IO) {
                repository.getRecipesFromFile(context)
            }
            _myRecipesList.value = myRecipes
        }
    }

    fun deleteRecipe(recipe: RecipeModel) {
        viewModelScope.launch {
            // Perform delete operation
            RecipeRepository.deleteRecipe(recipe)
            _deleteResult.value = true
        }
    }
    // Fetch favorite recipes from repository and update LiveData
//    fun fetchFavoriteRecipes() {
//        val favoriteRecipesFromRepo = repository.getFavoriteRecipes()
//        _favoriteRecipes.postValue(favoriteRecipesFromRepo)
//    }
//
//    // Add a favorite recipe to the list and update LiveData
//    fun addFavoriteRecipe(recipe: RecipeModel) {
//        repository.addToFavorites(recipe)
//        fetchFavoriteRecipes() // Update the list after adding
//    }
//
//    // Remove a favorite recipe from the list and update LiveData
//    fun removeFavoriteRecipe(recipe: RecipeModel) {
//        repository.removeFromFavorites(recipe)
//        fetchFavoriteRecipes() // Update the list after removing
//    }
//just a test to display favorites

    // Other methods for profile-related functionalities
}


