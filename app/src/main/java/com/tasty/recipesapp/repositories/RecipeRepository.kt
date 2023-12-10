//package com.tasty.recipesapp.repositories
//
//import com.google.gson.Gson
//import com.tasty.recipesapp.data.RecipeDao
//import com.tasty.recipesapp.data.RecipeEntity
//import com.tasty.recipesapp.dtos.recipeDto
//import com.tasty.recipesapp.models.RecipeListViewModel
//import org.json.JSONObject
//
//class RecipeRepository(private val recipeDao: RecipeDao) {
//
//    private val gson = Gson() // Initialize Gson instance
//
//    suspend fun insertRecipe(recipe: RecipeEntity) {
//        recipeDao.insertRecipe(recipe)
//    }
//
//    suspend fun getAllRecipes(): List<Unit> {
//        return recipeDao.getAllRecipes().map {
//            //val jsonObject = JSONObject(it.json)
//            //jsonObject.apply { put("id", it.internalId) }
//           // gson.fromJson(jsonObject.toString(), recipeDto::class.java).toModel()
//        }
//    }
//}
