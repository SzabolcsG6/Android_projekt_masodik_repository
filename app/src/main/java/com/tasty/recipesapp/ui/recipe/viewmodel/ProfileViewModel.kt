package com.tasty.recipesapp.ui.recipe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.repository.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: RecipeRepository) : ViewModel() {

    private val _favoriteRecipes = MutableLiveData<List<RecipeModel>>()
    val favoriteRecipes: LiveData<List<RecipeModel>> get() = _favoriteRecipes

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

    // Other methods for profile-related functionalities
}


