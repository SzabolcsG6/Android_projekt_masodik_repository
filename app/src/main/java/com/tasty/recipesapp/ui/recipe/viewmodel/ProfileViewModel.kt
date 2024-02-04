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
import android.util.Log
import com.tasty.recipesapp.repository.recipe.enitity.RecipeEntity

class ProfileViewModel(private val repository: RecipeRepository, private val context: Context) : ViewModel() {
    private val _recipes = MutableLiveData<List<RecipeEntity>>()
    val recipes: LiveData<List<RecipeEntity>> = _recipes
    //private val _favoriteRecipes = MutableLiveData<List<RecipeModel>>()
   // val favoriteRecipes: LiveData<List<RecipeModel>> get() = _favoriteRecipes
    private val _myRecipesList: MutableLiveData<List<RecipeModel>> = MutableLiveData()
    val myRecipesList: LiveData<List<RecipeModel>> get() = _myRecipesList
    private val _insertResult = MutableLiveData<Boolean>()
    val insertResult: LiveData<Boolean> get() = _insertResult
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
    fun fetchRecipesFromDatabase() {
        viewModelScope.launch {
            try {
                val recipesFromDb = repository.getAllRecipesFromDatabase()
                recipesFromDb.observeForever { recipes ->
                    _recipes.postValue(recipes)
                }
            } catch (e: Exception) {
                // Handle the exception or log it
                Log.e("ProfileViewModel", "Error fetching recipes from database: ${e.message}")
            }
        }
    }
    fun deleteRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            // Perform delete operation
            RecipeRepository.deleteRecipe(recipe)
            _deleteResult.value = true
        }
    } fun insertRecipeToDatabase(recipe: RecipeEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.insertRecipeDatabase(recipe)
            }
            _insertResult.postValue(true) // Notify observers about the successful insert
        }
    }

    fun getRecipesSortedByRatingDatabase(): List<RecipeModel> {
        return _myRecipesList.value?.sortedByDescending { it.userRatings.score } ?: emptyList()
    }

    fun getRecipesSortedByRatingAscendingDatabase(): List<RecipeModel> {
        return _myRecipesList.value?.sortedBy { it.userRatings.score } ?: emptyList()
    }

    fun getRecipesSortedByNameDatabase(): List<RecipeModel> {
        return _myRecipesList.value?.sortedBy { it.name } ?: emptyList()
    }

    fun getRecipesSortedByNameAscendingDatabase(): List<RecipeModel> {
        return _myRecipesList.value?.sortedByDescending { it.name } ?: emptyList()
    }

}


