package com.tasty.recipesapp.ui.recipe.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tasty.recipesapp.repository.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel

class RecipeDetailViewModel : ViewModel() {
    private val repository = RecipeRepository

    // Live data member
    var recipe: MutableLiveData<RecipeModel> = MutableLiveData()

    fun fetchRecipeDetail(recipeId: Int) {
        val recipe = repository.getRecipe(recipeId)
        this.recipe.value = recipe
    }
}