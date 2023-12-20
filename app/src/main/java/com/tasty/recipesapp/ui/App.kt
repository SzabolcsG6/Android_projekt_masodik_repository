package com.tasty.recipesapp.ui

import android.app.Application
import com.tasty.recipesapp.repository.RecipeRepository
import com.tasty.recipesapp.repository.recipe.RecipeDatabase

class App : Application() {

    private val database by lazy { RecipeDatabase.getDatabase(this) }
    //    val repository by lazy { RecipeRepository(database.recipeDao()) }
    val repository by lazy {
        RecipeRepository.initialize(database)
        RecipeRepository }

}