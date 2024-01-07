package com.tasty.recipesapp.repository.recipe.enitity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "recipe")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val internalId: Long = 0L,
    @SerializedName("json_data")
    val json: String
    //var isFavorite: Boolean = false//new
)
