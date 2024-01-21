package com.tasty.recipesapp.ui.profile.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.repository.RecipeRepository
import com.tasty.recipesapp.repository.recipe.enitity.RecipeEntity
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(private val repository: RecipeRepository,private val context: Context) : ViewModel() {

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

    /**
     * Insert into repo's list.
     */
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

    /**
     * Insert into database.
     */
//    fun insertRecipe(recipe: RecipeEntity) {
//        viewModelScope.launch {
//            val isSuccessFull = withContext(Dispatchers.IO) {
//                repository.insertRecipe(recipe)
//            }
//            insertResult.value = isSuccessFull
//        }
//    }
//
//    /**
//     * Delete from database.
//     */
//    fun deleteRecipe(recipe: RecipeEntity) {
//        viewModelScope.launch {
//            val isSuccessFull = withContext(Dispatchers.IO) {
//                repository.deleteRecipe(recipe)
//            }
//            deleteResult.value = isSuccessFull
//        }
//    }
}

