package com.tasty.recipesapp.ui.recipe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.repository.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: RecipeRepository) : ViewModel() {
//    private val _favoriteRecipes = MutableLiveData<List<RecipeModel>>()
//    val favoriteRecipes: LiveData<List<RecipeModel>> get() = _favoriteRecipes
//
//    // Function to fetch favorite recipes from the repository
//    fun fetchFavoriteRecipesFromRepository() {
//        viewModelScope.launch {
//            try {
//                val favoriteRecipes = repository.getFavoriteRecipes()
//                _favoriteRecipes.value = favoriteRecipes
//            } catch (e: Exception) {
//                // Handle errors appropriately
//            }
//        }
//    }
}

