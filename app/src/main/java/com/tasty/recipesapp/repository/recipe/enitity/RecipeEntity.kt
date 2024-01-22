package com.tasty.recipesapp.repository.recipe.enitity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.tasty.recipesapp.repository.recipe.model.RecipeModel

@Entity(tableName = "recipe")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val internalId: Long = 0L,
    @SerializedName("json_data")
    val json: String
    //var isFavorite: Boolean = false
)
fun RecipeEntity.toRecipeModel(): RecipeModel {
    val gson = Gson()
    val recipeModel = gson.fromJson(json, RecipeModel::class.java)
    return recipeModel.copy(id = internalId.toInt())
}
