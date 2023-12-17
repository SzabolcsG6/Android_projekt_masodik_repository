package com.tasty.recipesapp.providers

import android.content.Context
import com.tasty.recipesapp.data.RecipeDao
import com.tasty.recipesapp.data.RecipeDatabase
import com.tasty.recipesapp.repositories.InstructionsRepository
import com.tasty.recipesapp.repositories.RecipeRepository


object RepositoryProvider {
    private lateinit var recipeDao: RecipeDao
    private lateinit var appContext: Context // Define the Context variable

    fun initialize(context: Context) {
        appContext = context // Assign the received context to appContext
        recipeDao = RecipeDatabase.getDatabase(context).recipeDao()
    }

    val recipeRepository: RecipeRepository by lazy {
        checkInitialized()
        RecipeRepository(recipeDao, appContext) // Pass the appContext when creating RecipeRepository
    }

    private fun checkInitialized() {
        if (!::recipeDao.isInitialized) {
            throw UninitializedPropertyAccessException("RepositoryProvider has not been initialized")
        }
    }

    val instructionsRepository: InstructionsRepository = InstructionsRepository()
}
