package com.tasty.recipesapp.repository.recipe.enitity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.tasty.recipesapp.repository.recipe.model.RecipeModel

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val internalId: Int = 0,
    val json: String
){
companion object {
    fun fromRecipeModel(recipeModel: RecipeModel): FavoriteEntity {
        // Convert RecipeModel to JSON string
        val json = Gson().toJson(recipeModel)
        return FavoriteEntity(json = json)
    }
}}