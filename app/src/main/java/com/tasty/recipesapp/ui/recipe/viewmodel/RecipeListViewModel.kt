package com.tasty.recipesapp.ui.recipe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.repository.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class RecipeListViewModel : ViewModel() {

    val repository = RecipeRepository
    private val searchChannel = ConflatedBroadcastChannel<String>()
    val recipeList: MutableLiveData<List<RecipeModel>> =
        MutableLiveData()

    private val _searchResults: MutableLiveData<List<RecipeModel>> = MutableLiveData()
    val searchResults: LiveData<List<RecipeModel>> get() = _searchResults

    fun getAllRecipesFromApi (){
        viewModelScope.launch {
            val recipes = withContext(Dispatchers.IO){
                repository.getRecipeFromApi("0", "50")
            }
            recipeList.value = recipes
        }
    }
    fun sortRecipesByRating() {
        viewModelScope.launch {
            val sortedRecipes = withContext(Dispatchers.IO) {
                RecipeRepository.getRecipesSortedByRating()
            }
            recipeList.value = sortedRecipes
        }
    }
    fun sortRecipesByName() {
        viewModelScope.launch {
            val sortedRecipes = withContext(Dispatchers.IO) {
                RecipeRepository.getRecipesSortedByName()
            }
            recipeList.value = sortedRecipes
        }
    }



}