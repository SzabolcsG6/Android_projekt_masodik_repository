package com.tasty.recipesapp.ui.profile.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
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
    private val _databaseRecipesList = MutableLiveData<List<RecipeEntity>>()
    val databaseRecipesList: LiveData<List<RecipeEntity>> get() = _databaseRecipesList


    // Live data members
    var myRecipesList: MutableLiveData<List<RecipeModel>> =
        MutableLiveData()

    var insertResult: MutableLiveData<Boolean> =
        MutableLiveData()

    var deleteResult: MutableLiveData<Boolean> =
        MutableLiveData()
    init {
        viewModelScope.launch {
            repository.initializeFromDatabase()
        }
    }

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
        fetchMyRecipesData()
    }

    fun insertRecipeToDatabase(recipe: RecipeEntity) {
        viewModelScope.launch {
            repository.insertRecipeDatabase(recipe)
        }
        fetchDatabaseRecipes()
    }

    fun deleteRecipe(recipe: RecipeModel) {
        val result = repository.deleteRecipe(recipe)
        deleteResult.value = result
    }

    fun fetchDatabaseRecipes() {
        viewModelScope.launch {
            // Use the getRecipesFromDatabase method to load recipes from the local database
            val databaseRecipes = withContext(Dispatchers.IO) {
                repository.getRecipesFromDatabase()
            }
            databaseRecipes.observeForever { databaseRecipes ->
                _databaseRecipesList.postValue(databaseRecipes)
            }
        }
    }

    /**
     * Insert into database.
     */
//    fun sortRecipesByRating() {
//        viewModelScope.launch {
//            val sortedRecipes = withContext(Dispatchers.IO) {
//                RecipeRepository.getRecipesSortedByRating()
//            }
//            _databaseRecipesList.value = sortedRecipes
//        }
//    }
//    fun sortRecipesByRatingAscending() {
//        viewModelScope.launch {
//            val sortedRecipes = withContext(Dispatchers.IO) {
//                RecipeRepository.getRecipesSortedByRatingAscending()
//            }
//            _databaseRecipesList.value = sortedRecipes
//        }
//    }
//    fun sortRecipesByName() {
//        viewModelScope.launch {
//            val sortedRecipes = withContext(Dispatchers.IO) {
//                RecipeRepository.getRecipesSortedByName()
//            }
//            _databaseRecipesList.value = sortedRecipes
//        }
//    }
//    fun sortRecipesByNameAscending() {
//        viewModelScope.launch {
//            val sortedRecipes = withContext(Dispatchers.IO) {
//                RecipeRepository.getRecipesSortedByNameAscending()
//            }
//            _databaseRecipesList.value = sortedRecipes
//        }
//    }
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
