package com.tasty.recipesapp.repositories

import com.google.gson.Gson
import com.tasty.recipesapp.data.RecipeDao
import com.tasty.recipesapp.data.RecipeEntity
import com.tasty.recipesapp.dtos.RecipeDto
import com.tasty.recipesapp.models.RecipeListViewModel
import org.json.JSONObject

class RecipeRepository(private val recipeDao: RecipeDao) {

    private val gson = Gson()

    suspend fun insertRecipe(recipe: RecipeEntity) {
        recipeDao.insertRecipe(recipe)
    }

    suspend fun getAllRecipes(): List<RecipeDto> {
        return recipeDao.getAllRecipes().map { recipeEntity ->
            val jsonObject = JSONObject(recipeEntity.json)
            jsonObject.put("id", recipeEntity.id)
            gson.fromJson(jsonObject.toString(), RecipeDto::class.java).toModel()
        }
    }
}

