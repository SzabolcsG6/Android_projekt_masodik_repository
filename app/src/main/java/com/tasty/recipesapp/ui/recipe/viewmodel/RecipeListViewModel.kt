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

internal class RecipeListViewModel : ViewModel() {

    val repository = RecipeRepository

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
}