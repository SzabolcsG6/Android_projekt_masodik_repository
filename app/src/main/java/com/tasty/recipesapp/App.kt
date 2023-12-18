package com.tasty.recipesapp

import android.app.Application
import com.tasty.recipesapp.data.RecipeDatabase
import com.tasty.recipesapp.repositories.RecipeRepository

class App : Application() {

    private val database by lazy { RecipeDatabase.getDatabase(this) }
    //    val repository by lazy { RecipeRepository(database.recipeDao()) }
    val repository by lazy {
        RecipeRepository.initialize(database)
        RecipeRepository }

}