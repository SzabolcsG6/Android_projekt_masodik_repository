package com.tasty.recipesapp.models

import androidx.lifecycle.MutableLiveData
import com.tasty.recipesapp.repositories.RecipeRepository

class RecipeDetailViewModel : ViewModel() {
    private val repository = RecipeRepository

    // Live data member
    var recipe: MutableLiveData<RecipeModel> = MutableLiveData()

    fun fetchRecipeDetail(recipeId: Int) {
        val recipe = repository.getRecipe(recipeId)
        this.recipe.value = recipe
    }
}
