package com.tasty.recipesapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class RecipeEntity(
    @PrimaryKey val id: String, // Assuming id is a string
    val json: String,
)
