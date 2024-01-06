package com.tasty.recipesapp.ui.recipe.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.ui.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel: ViewModel() {
    //val db = App.db
    //var favoritesRecipes = MutableLiveData<List<recipeList>>()

    fun loadFavoriteRecipes(){
        viewModelScope.launch(Dispatchers.Main) {
           // val result = db.getRecipesDao().getAllRecipes()
            //favoritesRecipes.value = result
        }
    }

//    fun deleteFromFavorite (recipe: recipeList){
//        db.getRecipesDao().deleteRecipe(recipe)
//        loadFavoriteRecipes()
//    }

}