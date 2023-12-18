package com.tasty.recipesapp.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.data.RecipeEntity
import com.tasty.recipesapp.repositories.RecipeRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: RecipeRepository) : ViewModel() {
    // Live data members
    var myRecipesList: MutableLiveData<List<RecipeModel>> =
        MutableLiveData()

    var insertResult: MutableLiveData<Boolean> =
        MutableLiveData()

    var deleteResult: MutableLiveData<Boolean> =
        MutableLiveData()
    fun fetchMyRecipesData() {
        val recipes = repository.getMyRecipes()
        myRecipesList.value = recipes
    }
    fun insertRecipe(recipe: RecipeModel) {
        val result = repository.insertRecipe(recipe)
        insertResult.value = result
    }
    fun insertRecipeToDatabase(recipe: RecipeEntity) {
        viewModelScope.launch {
            repository.insertRecipeDatabase(recipe)
        }
    }
    fun deleteRecipe(recipe: RecipeModel) {
        val result = repository.deleteRecipe(recipe)
        deleteResult.value = result
    }

}