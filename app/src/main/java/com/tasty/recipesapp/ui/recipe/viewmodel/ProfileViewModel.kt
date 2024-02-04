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
    private val _favoriteRecipes = MutableLiveData<List<RecipeModel>>()
    val favoriteRecipes: LiveData<List<RecipeModel>> get() = _favoriteRecipes
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


    //    fun fetchDatabaseRecipes() {
//        viewModelScope.launch {
//            // Use the getRecipesFromDatabase method to load recipes from the local database
//            val databaseRecipes = withContext(Dispatchers.IO) {
//                repository.getRecipesFromDatabase()
//            }
//            _myRecipesList.value = databaseRecipes.map { it.toRecipeModel() }
//        }
//    }
//fun getAllRecipesFromDatabase() {
//    repository.getAllRecipesFromDatabase(object : RecipeRepository.Callback {
//        override fun onRecipesLoaded(recipes: List<RecipeModel>) {
//            _myRecipesList.value = recipes
//        }
//    })
//}
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
//just a test to display favorites

//    // Other methods for profile-related functionalities
//    private val _databaseRecipesList = MutableLiveData<List<RecipeEntity>>()
//    val databaseRecipesList: LiveData<List<RecipeEntity>> get() = _databaseRecipesList
//
//
//    // Live data members
//    var myRecipesList: MutableLiveData<List<RecipeModel>> =
//        MutableLiveData()
//
//    var insertResult: MutableLiveData<Boolean> =
//        MutableLiveData()
//
//    var deleteResult: MutableLiveData<Boolean> =
//        MutableLiveData()
//    init {
//        viewModelScope.launch {
//            repository.initializeFromDatabase()
//        }
//    }
//
//    fun fetchMyRecipesData() {
//        val recipes = repository.getMyRecipes()
//        myRecipesList.value = recipes
//    }
//
//    /**
//     * Insert into repo's list.
//     */
//    fun insertRecipe(recipe: RecipeModel) {
//        val result = repository.insertRecipe(recipe)
//
//        insertResult.value = result
//        fetchMyRecipesData()
//    }
//
//
//
//    fun deleteRecipe(recipe: RecipeModel) {
//        val result = repository.deleteRecipe(recipe)
//        deleteResult.value = result
//    }
//
//    fun fetchDatabaseRecipes() {
//        viewModelScope.launch {
//            // Use the getRecipesFromDatabase method to load recipes from the local database
//            val databaseRecipes = withContext(Dispatchers.IO) {
//                repository.getRecipesFromDatabase()
//            }
//            databaseRecipes.observeForever { databaseRecipes ->
//                _databaseRecipesList.postValue(databaseRecipes)
//            }
//        }
//    }
//

    /**
     * Insert into database.
     */
}


