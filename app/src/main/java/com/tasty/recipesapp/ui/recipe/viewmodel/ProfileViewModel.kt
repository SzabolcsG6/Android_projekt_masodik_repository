package com.tasty.recipesapp.ui.recipe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.tasty.recipesapp.repository.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.repository.recipe.enitity.RecipeEntity
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: RecipeRepository) : ViewModel() {
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

//display recipes from database
    private val _recipeListLiveData = MutableLiveData<List<RecipeEntity>>()
    val recipeListLiveData: LiveData<List<RecipeEntity>> get() = _recipeListLiveData

//    fun fetchMyRecipesData() {
//        viewModelScope.launch {
//            // Use the getRecipesFromFile method to load recipes from the file
//            val myRecipes = withContext(Dispatchers.IO) {
//                repository.getRecipesFromFile(context)
//            }
//            _myRecipesList.value = myRecipes
//        }
//    }

    fun fetchRecipesFromDatabase() {
        viewModelScope.launch {
            repository.getAllRecipesFromDatabase().observeForever { recipeEntities ->
                val recipeModels = recipeEntities.mapIndexed { index, entity ->
                    val gson = Gson()
                    val recipeModel = gson.fromJson(entity.json, RecipeModel::class.java)
                    recipeModel.copy(
                        id = index, // Assuming index is unique for each recipe
                        aspect_ratio = recipeModel.aspect_ratio ?: "16:9", // Default aspect ratio
                        description = recipeModel.description ?: "Default description" // Default description
                        //thumbnailUrl = recipeModel.thumbnailUrl,
                       // userRatings = recipeModel.userRatings,
                       // total_time_tier = recipeModel.total_time_tier,
                       // instructions = recipeModel.instructions
                    )
                }
                _myRecipesList.postValue(recipeModels)
            }
        }
    }


    //    fun loadRecipes() {
//        viewModelScope.launch {
//            _recipeListLiveData.value = recipeDAO.getAllRecipes()
//        }
//    }
//    fun deleteRecipe(recipe: RecipeEntity) {
//        viewModelScope.launch {
//            // Perform delete operation
//            RecipeRepository.deleteRecipe(recipe)
//            _deleteResult.value = true
//        }}
//    } fun insertRecipeToDatabase(recipe: RecipeEntity) {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                repository.insertRecipeDatabase(recipe)
//            }
//            _insertResult.postValue(true) // Notify observers about the successful insert
//        }
//    }
    fun deleteRecipe(recipe: RecipeModel) {
        viewModelScope.launch {
            repository.deleteRecipe(recipe)
        }
    }
    fun deleteAllRecipes(){
        viewModelScope.launch {
            repository.deleteAllRecipes()
        }
    }
suspend fun insertRecipeToDatabase(recipe: RecipeEntity) {
    repository.insertRecipeDatabase(recipe)
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


