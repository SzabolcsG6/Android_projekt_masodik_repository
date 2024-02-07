package com.tasty.recipesapp.ui

import android.app.Application
import com.tasty.recipesapp.repository.RecipeRepository
import com.tasty.recipesapp.repository.recipe.FavoriteDatabase
import com.tasty.recipesapp.repository.recipe.RecipeDatabase

class App : Application() {

    private val database by lazy { RecipeDatabase.getDatabase(this) }
    private val favdatabase by lazy { FavoriteDatabase.getDatabase(this) }
    val repository by lazy {
        RecipeRepository.initialize(database,favdatabase)
        RecipeRepository }

}